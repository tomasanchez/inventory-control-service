package org.inventory.control.services;

import org.inventory.api.shipment.MeliAdapter;
import org.inventory.api.shipment.ShippingInformer;
import org.inventory.control.model.product.Product;
import org.inventory.control.model.product.Shipping;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

@Service
public class ProductProvider {

    private ShippingInformer informer;

    public ProductProvider() {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        this.informer = new MeliAdapter(builder.build());
    }

    public ProductProvider(ShippingInformer informer) {
        this.informer = informer;
    }

    public Shipping getProductShippingInfo(Product product) {
        return informer.getShipment(product.getCode());
    }
}
