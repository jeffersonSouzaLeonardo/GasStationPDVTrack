package com.br.manager.domain.integration.horustech;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/** Builds and validates Horustech frames (DT 214): {@code >PCCCCXXXX...KK}. */
@Component
public class HorustechFrameCodec {

    public static final int HEADER_LENGTH = 6;
    public static final int CHECKSUM_LENGTH = 2;

    private static final char START = '>';
    private static final char QUERY = '?';
    private static final char ANSWER = '!';
    private static final int CHECKSUM_MASK = 0xFF;

    /** Encodes a query whose data is the command index followed by its parameters. */
    public byte[] encodeQuery(String data) {
        String body = QUERY + String.format("%04X", data.length()) + data;
        return (START + body + checksum(body)).getBytes(StandardCharsets.US_ASCII);
    }

    /** Reads the data length from an answer header ({@code >!CCCC}). */
    public int dataLength(String header) {
        if (header.length() != HEADER_LENGTH || header.charAt(0) != START || header.charAt(1) != ANSWER) {
            throw new HorustechException("Invalid frame header received: " + header);
        }
        try {
            return Integer.parseInt(header.substring(2), 16);
        } catch (NumberFormatException e) {
            throw new HorustechException("Invalid frame length received: " + header, e);
        }
    }

    /** Validates the checksum of a complete answer and returns its data (command index + parameters). */
    public String decodeAnswer(String frame) {
        int length = dataLength(frame.substring(0, HEADER_LENGTH));
        if (frame.length() != HEADER_LENGTH + length + CHECKSUM_LENGTH) {
            throw new HorustechException("Frame size does not match its header: " + frame);
        }
        int checksumStart = frame.length() - CHECKSUM_LENGTH;
        String expected = checksum(frame.substring(1, checksumStart));
        if (!expected.equalsIgnoreCase(frame.substring(checksumStart))) {
            throw new HorustechException("Invalid frame checksum: " + frame);
        }
        return frame.substring(HEADER_LENGTH, checksumStart);
    }

    /** Sum of the ASCII values after the start delimiter, keeping only the least significant byte. */
    String checksum(String body) {
        int sum = 0;
        for (char c : body.toCharArray()) {
            sum += c;
        }
        return String.format("%02X", sum & CHECKSUM_MASK);
    }
}
