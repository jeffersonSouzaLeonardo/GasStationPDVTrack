package com.br.manager.integration.horustech;

import com.br.manager.config.HorustechProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Thread-safe TCP client of the Horustech concentrator (DT 214).
 * The connection is re-established lazily after a communication failure.
 */
@Component
public class HorustechClient {

    private static final String READ_FUELING = "02";
    private static final String NEXT_FUELING = "06";

    private final HorustechProperties properties;
    private final HorustechFrameCodec codec;

    private Socket socket;
    private boolean enabled;

    public HorustechClient(HorustechProperties properties, HorustechFrameCodec codec) {
        this.properties = properties;
        this.codec = codec;
    }

    public synchronized void open() {
        enabled = true;
        connect();
    }

    public synchronized void close() {
        enabled = false;
        disconnect();
    }

    public synchronized boolean isOpen() {
        return socket != null && socket.isConnected() && !socket.isClosed();
    }

    /** Reads the finished fueling at the read pointer, without moving it (command 02). */
    public synchronized String readFueling() {
        return execute(READ_FUELING);
    }

    /** Moves the read pointer to the next fueling (command 06). */
    public synchronized String nextFueling() {
        return execute(NEXT_FUELING);
    }

    private String execute(String data) {
        if (!enabled) {
            throw new HorustechException("Connection with the concentrator is not open");
        }
        if (!isOpen()) {
            connect();
        }
        try {
            OutputStream out = socket.getOutputStream();
            out.write(codec.encodeQuery(data));
            out.flush();
            String answer = codec.decodeAnswer(readFrame(socket.getInputStream()));
            if (!answer.startsWith(data)) {
                throw new HorustechException("Unexpected answer for command " + data + ": " + answer);
            }
            return answer;
        } catch (IOException e) {
            disconnect();
            throw new HorustechException("Communication failure with the concentrator", e);
        } catch (HorustechException e) {
            // The stream may be out of sync, so a clean connection is needed for the next command
            disconnect();
            throw e;
        }
    }

    private String readFrame(InputStream in) throws IOException {
        String header = new String(readFully(in, HorustechFrameCodec.HEADER_LENGTH), StandardCharsets.US_ASCII);
        int remaining = codec.dataLength(header) + HorustechFrameCodec.CHECKSUM_LENGTH;
        return header + new String(readFully(in, remaining), StandardCharsets.US_ASCII);
    }

    private byte[] readFully(InputStream in, int length) throws IOException {
        byte[] buffer = in.readNBytes(length);
        if (buffer.length < length) {
            throw new IOException("Connection closed by the concentrator");
        }
        return buffer;
    }

    private void connect() {
        disconnect();
        Socket candidate = new Socket();
        try {
            candidate.connect(new InetSocketAddress(properties.getHost(), properties.getPort()),
                    properties.getConnectTimeoutMs());
            candidate.setSoTimeout(properties.getReadTimeoutMs());
            candidate.setTcpNoDelay(true);
            socket = candidate;
        } catch (IOException e) {
            try {
                candidate.close();
            } catch (IOException ignored) {
                // nothing else to release
            }
            throw new HorustechException("Unable to connect to the concentrator at "
                    + properties.getHost() + ":" + properties.getPort(), e);
        }
    }

    private void disconnect() {
        if (socket == null) {
            return;
        }
        try {
            socket.close();
        } catch (IOException ignored) {
            // the socket is discarded anyway
        }
        socket = null;
    }
}
