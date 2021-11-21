package org.inventory.api.shipment;

import org.inventory.control.model.product.Shipping;
import org.springframework.web.client.RestTemplate;

public interface ShippingInformer {

    ShippingInformer build(RestTemplate template);

    Shipping getShipment(String product);
}
