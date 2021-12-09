package org.inventory.control.model.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductTest {

    private Product product;


    @BeforeEach
    void beginTransaction() {
        product = new Product();
    }


    @Test
    void productStockIsSumOfStockInInventories() {
        int units = 10;
        int inventories = 3;

        for (int i = 0; i < inventories; i++) {
            product.addToInventory(new Inventory().setUnits(units));
        }

        assertEquals(units * inventories, product.getStock());
    }



}
