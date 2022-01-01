package com.ztar.shipment.service;

import com.ztar.shipment.dto.PackageDetails;
import com.ztar.shipment.dto.ShipmentRequest;
import com.ztar.shipment.log.LogExecutionTime;
import com.ztar.shipment.model.Shipment;
import com.ztar.shipment.model.Status;
import com.ztar.shipment.model.Unit;
import com.ztar.shipment.util.UnitConverter;

import java.math.BigDecimal;

import static com.ztar.shipment.model.Unit.INCH;

public interface ShipmentService {

    @LogExecutionTime
    default Shipment submitShipment(ShipmentRequest request) {
        Shipment shipment = new Shipment();
        shipment.setCarrier(request.getShipmentServiceId());
        shipment.setFees(calculateFees(request));
        shipment.setStatus(Status.CONFIRMED);
        return save(shipment);
    }

    Shipment save(Shipment shipment);

    BigDecimal calculateFees(ShipmentRequest request);

    default Integer getDimensionalWeight(PackageDetails packageDetails) {
        Integer length = packageDetails.getLength();

        Integer height = packageDetails.getHeight();

        Integer width = packageDetails.getWidth();

        if (getLengthUnit() != INCH) length = (int) Math.ceil(UnitConverter.convert(length, getLengthUnit(), INCH));

        if (getHeightUnit() != INCH) height = (int) Math.ceil(UnitConverter.convert(height, getHeightUnit(), INCH));

        if (getWidthUnit() != INCH) width = (int) Math.ceil(UnitConverter.convert(width, getWidthUnit(), INCH));

        Integer cubicSize = length * width * height;

        return cubicSize / getDIMDivisor();
    }

    Unit getWeightUnit();

    Unit getWidthUnit();

    Unit getHeightUnit();

    Unit getLengthUnit();

    Integer getDIMDivisor();

    BigDecimal getPricePerPound();
}
