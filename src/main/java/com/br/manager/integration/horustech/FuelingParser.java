package com.br.manager.integration.horustech;

import com.br.manager.dto.FuelingDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Parses the answer data of the "fueling reading" command (DT 214, 3.1.1):
 * 02 NNNNNN BB CC AA TTTTTT LLLLLL PPPP X Y Z tttt dd mm yy hh nn eeeeeeeeee EEEEEEEEEE
 * iiiiiiiiiiiiiiii IIIIIIIIIIIIIIII aaaaaaaa.
 * An answer containing only the command index means there is no fueling to read.
 */
@Component
public class FuelingParser {

    private static final int INDEX_LENGTH = 2;
    private static final int DATA_LENGTH = 105;
    private static final int YEAR_BASE = 2000;

    public Optional<FuelingDTO> parse(String answer) {
        if (answer == null || answer.length() <= INDEX_LENGTH) {
            return Optional.empty();
        }
        if (answer.length() != INDEX_LENGTH + DATA_LENGTH) {
            throw new HorustechException("Unexpected fueling data received: " + answer);
        }
        Cursor c = new Cursor(answer.substring(INDEX_LENGTH));
        try {
            long registry = Long.parseLong(c.next(6));
            String nozzleCode = c.next(2);
            String fuelCode = c.next(2);
            String tankNumber = c.next(2);
            String total = c.next(6);
            String volume = c.next(6);
            String price = c.next(4);
            int totalScale = Integer.parseInt(c.next(1));
            int volumeScale = Integer.parseInt(c.next(1));
            int priceScale = Integer.parseInt(c.next(1));
            int durationSeconds = Integer.parseInt(c.next(4));
            int day = Integer.parseInt(c.next(2));
            int month = Integer.parseInt(c.next(2));
            int year = YEAR_BASE + Integer.parseInt(c.next(2));
            int hour = Integer.parseInt(c.next(2));
            int minute = Integer.parseInt(c.next(2));
            String initialMeter = c.next(10);
            String finalMeter = c.next(10);
            String attendantId = c.next(16);
            String customerId = c.next(16);

            return Optional.of(new FuelingDTO(
                    registry,
                    nozzleCode,
                    fuelCode,
                    tankNumber,
                    decimal(total, totalScale),
                    decimal(volume, volumeScale),
                    decimal(price, priceScale),
                    durationSeconds,
                    LocalDateTime.of(year, month, day, hour, minute),
                    decimal(initialMeter, volumeScale),
                    decimal(finalMeter, volumeScale),
                    attendantId,
                    customerId,
                    answer));
        } catch (NumberFormatException | DateTimeException e) {
            throw new HorustechException("Invalid fueling data received: " + answer, e);
        }
    }

    private BigDecimal decimal(String digits, int scale) {
        return new BigDecimal(digits).movePointLeft(scale);
    }

    /** Sequential reader of fixed-width fields. */
    private static final class Cursor {
        private final String data;
        private int position;

        private Cursor(String data) {
            this.data = data;
        }

        private String next(int width) {
            String field = data.substring(position, position + width);
            position += width;
            return field;
        }
    }
}
