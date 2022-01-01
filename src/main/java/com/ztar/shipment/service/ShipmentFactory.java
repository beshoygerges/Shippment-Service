package com.ztar.shipment.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class ShipmentFactory {

    private final ShipmentService fedExShipmentService;
    private final ShipmentService upsShipmentService;

    public ShipmentFactory(@Qualifier("fedEx") ShipmentService fedExShipmentService, @Qualifier("ups") ShipmentService upsShipmentService) {
        this.fedExShipmentService = fedExShipmentService;
        this.upsShipmentService = upsShipmentService;
    }

    public ShipmentService getShipmentService(String serviceId) {
        switch (serviceId) {
            case "fedexAir":
            case "fedexGround":
                return fedExShipmentService;
            case "UPSExpress":
            case "UPS2DAY":
                return upsShipmentService;
            default:
                throw new IllegalArgumentException("invalid value for service Id: " + serviceId);
        }
    }

}
