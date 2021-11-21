package org.inventory.api.shipment;

import java.util.HashMap;
import java.util.Map;
import org.inventory.control.model.product.Shipping;
import org.springframework.web.client.RestTemplate;

public class MeliAdapter implements ShippingInformer {

    private final RestTemplate webClient;
    private final String url = "https://api.mercadolibre.com/items/";

    public MeliAdapter(RestTemplate webClient) {
        this.webClient = webClient;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Shipping getShipment(String productCode) {
        Shipping shipping;

        try {
            Map<String, Object> shippingMap = new HashMap<>();
            shippingMap = (Map<String, Object>) webClient
                    .getForObject(url.concat(productCode), shippingMap.getClass()).get("shipping");
            shipping = new Shipping().setMode((String) shippingMap.get("mode"))
                    .setLocalPickUp((Boolean) shippingMap.get("local_pick_up"))
                    .setStorePickUp((Boolean) shippingMap.get("store_pick_up"))
                    .setType((String) shippingMap.get("logistic_type"));
        } catch (Exception e) {
            shipping = null;
        }

        return shipping;
    }

    @Override
    public ShippingInformer build(RestTemplate template) {
        return new MeliAdapter(template);
    }

}
