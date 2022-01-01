package com.ztar.shipment.controller;

import com.ztar.shipment.dto.ShipmentRequest;
import com.ztar.shipment.dto.ShipmentResponse;
import com.ztar.shipment.model.Shipment;
import com.ztar.shipment.service.ShipmentFactory;
import com.ztar.shipment.service.ShipmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/shipments")
public class ShipmentController {

    private final ShipmentFactory shipmentFactory;
    private final ModelMapper modelMapper;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ShipmentResponse submitShipment(@Valid @RequestBody ShipmentRequest request) {
        ShipmentService shipmentService = shipmentFactory.getShipmentService(request.getShipmentServiceId());
        Shipment shipment = shipmentService.submitShipment(request);
        return modelMapper.map(shipment, ShipmentResponse.class);
    }
}
