package com.ztar.shipment;

import com.ztar.shipment.dto.PackageDetails;
import com.ztar.shipment.dto.ShipmentRequest;
import com.ztar.shipment.model.Shipment;
import com.ztar.shipment.model.Status;
import com.ztar.shipment.repository.ShipmentRepository;
import com.ztar.shipment.service.impl.FedExShipmentImpl;
import com.ztar.shipment.service.impl.UpsShipmentImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;

@ExtendWith({MockitoExtension.class})
@MockitoSettings(strictness = Strictness.LENIENT)
public class ShipmentServiceApplicationTests {

    @InjectMocks
    private UpsShipmentImpl upsShipment;

    @InjectMocks
    private FedExShipmentImpl fedExShipment;

    @Mock
    private ShipmentRepository shipmentRepository;

    public ShipmentServiceApplicationTests() {
    }

    @Test
    public void submitUpsShipmentShouldPass() {
        ShipmentRequest request = ShipmentRequest.builder().shipmentServiceId("UPSExpress").packageDetails(PackageDetails.builder().height(100).length(100).weight(200).width(100).build()).build();
        BDDMockito.given(this.shipmentRepository.save(ArgumentMatchers.any(Shipment.class))).willAnswer((invocationOnMock) -> invocationOnMock.getArgument(0));
        Shipment savedShipment = this.upsShipment.submitShipment(request);
        Assertions.assertThat(savedShipment).isNotNull();
        Assertions.assertThat(savedShipment.getUuid()).isNotNull();
        Assertions.assertThat(savedShipment.getCarrier()).isEqualTo("UPSExpress");
        Assertions.assertThat(savedShipment.getStatus()).isEqualTo(Status.CONFIRMED);
        Assertions.assertThat(savedShipment.getFees().doubleValue()).isEqualTo(BigDecimal.valueOf(719L).doubleValue());
    }

    @Test
    public void submitFedExShipmentShouldPass() {
        ShipmentRequest request = ShipmentRequest.builder().shipmentServiceId("fedexGround").packageDetails(PackageDetails.builder().height(100).length(100).weight(200).width(100).build()).build();
        BDDMockito.given(this.shipmentRepository.save(ArgumentMatchers.any(Shipment.class))).willAnswer((invocationOnMock) -> invocationOnMock.getArgument(0));
        Shipment savedShipment = this.fedExShipment.submitShipment(request);
        Assertions.assertThat(savedShipment).isNotNull();
        Assertions.assertThat(savedShipment.getUuid()).isNotNull();
        Assertions.assertThat(savedShipment.getCarrier()).isEqualTo("fedexGround");
        Assertions.assertThat(savedShipment.getStatus()).isEqualTo(Status.CONFIRMED);
        Assertions.assertThat(savedShipment.getFees().doubleValue()).isEqualTo(BigDecimal.valueOf(69L).doubleValue());
    }
}