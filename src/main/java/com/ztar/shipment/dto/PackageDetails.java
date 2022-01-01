package com.ztar.shipment.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PackageDetails {
    @NotNull(message = "width should not be null")
    @Min(value = 1, message = "width must be greater than 1")
    private Integer width;

    @NotNull(message = "height should not be null")
    @Min(value = 1, message = "height must be greater than 1")
    private Integer height;

    @NotNull(message = "length should not be null")
    @Min(value = 1, message = "length must be greater than 1")
    private Integer length;

    @NotNull(message = "weight should not be null")
    @Min(value = 1, message = "weight must be greater than 1")
    private Integer weight;
}
