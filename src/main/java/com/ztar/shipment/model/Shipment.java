package com.ztar.shipment.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Shipment extends BaseEntity {
    @Column(nullable = false)
    private String carrier;

    @Column(nullable = false)
    private BigDecimal fees;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
}
