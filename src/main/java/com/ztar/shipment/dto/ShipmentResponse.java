package com.ztar.shipment.dto;

import com.ztar.shipment.model.Status;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class ShipmentResponse {
    private UUID uuid;
    private Status status;
    private BigDecimal fees;
}
