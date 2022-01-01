package com.ztar.shipment.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ShipmentRequest {
    @NotBlank(message = "You have to provide a valid value for shipment service ID")
    private String shipmentServiceId;
    @Valid
    @NotNull(message = "package Details should not be null")
    private PackageDetails packageDetails;
}
