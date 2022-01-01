package com.ztar.shipment.service.impl;

import com.ztar.shipment.dto.PackageDetails;
import com.ztar.shipment.dto.ShipmentRequest;
import com.ztar.shipment.model.Shipment;
import com.ztar.shipment.model.Unit;
import com.ztar.shipment.repository.ShipmentRepository;
import com.ztar.shipment.service.ShipmentService;
import com.ztar.shipment.util.UnitConverter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.ztar.shipment.model.Unit.POUND;

@AllArgsConstructor
@Service
@Qualifier("fedEx")
public class FedExShipmentImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;

    @Override
    public Shipment save(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }

    @Override
    public BigDecimal calculateFees(ShipmentRequest request) {
        PackageDetails packageDetails = request.getPackageDetails();

        Integer dimensionalWeight = getDimensionalWeight(packageDetails);

        Integer weight = packageDetails.getWeight();

        if (getWeightUnit() != POUND) weight = (int) Math.ceil(UnitConverter.convert(weight, getWeightUnit(), POUND));

        if (weight > dimensionalWeight) return BigDecimal.valueOf(weight / 10).multiply(getPricePerPound());

        return BigDecimal.valueOf(dimensionalWeight / 10).multiply(getPricePerPound());
    }

    @Override
    public Unit getWeightUnit() {
        return Unit.GRAM;
    }

    @Override
    public Unit getWidthUnit() {
        return Unit.CM;
    }

    @Override
    public Unit getHeightUnit() {
        return Unit.CM;
    }

    @Override
    public Unit getLengthUnit() {
        return Unit.CM;
    }

    @Override
    public Integer getDIMDivisor() {
        return 138;
    }

    @Override
    public BigDecimal getPricePerPound() {
        return BigDecimal.valueOf(1.5);
    }
}
