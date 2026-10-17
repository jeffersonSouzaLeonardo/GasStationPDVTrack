package com.br.manager.domain.integration.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Fueling read from the concentrator memory.
 *
 * @param registry        position of the record in the concentrator memory
 * @param nozzleCode      nozzle that performed the fueling
 * @param fuelCode        fuel type code (DT 214, chapter 6)
 * @param tankNumber      tank that supplies the nozzle
 * @param totalAmount     total value of the sale
 * @param volume          fueled volume in liters
 * @param unitPrice       price per liter
 * @param durationSeconds duration of the fueling
 * @param occurredAt      date and time of the fueling
 * @param initialMeter    pump totalizer before the fueling
 * @param finalMeter      pump totalizer after the fueling
 * @param attendantId     attendant identifier
 * @param customerId      customer identifier
 * @param rawData         original answer data received from the concentrator
 */
public record FuelingDTO(
        long registry,
        String nozzleCode,
        String fuelCode,
        String tankNumber,
        BigDecimal totalAmount,
        BigDecimal volume,
        BigDecimal unitPrice,
        int durationSeconds,
        LocalDateTime occurredAt,
        BigDecimal initialMeter,
        BigDecimal finalMeter,
        String attendantId,
        String customerId,
        String rawData) {
}
