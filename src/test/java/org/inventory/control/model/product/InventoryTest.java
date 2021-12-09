package org.inventory.control.model.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import org.inventory.control.exceptions.LocationFullException;
import org.inventory.control.model.warehouse.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class InventoryTest {

    private Inventory inventory;
    private Location location;

    @BeforeEach
    void beginTransaction() {
        inventory = new Inventory();
        location = Mockito.mock(Location.class);
    }


    @Test
    void inventoryStoresUnits() {
        inventory.addUnits(Location.MAX_UNITS);
        assertEquals(Location.MAX_UNITS, inventory.getUnits());
    }

    @Test
    void inventoryDoesNotStoreUnitsWhenExceeds() {
        assertThrows(LocationFullException.class, () -> {
            inventory.addUnits(Location.MAX_UNITS + 1);
        });
    }

    @Test
    void inventoryStoresWhenLocationHasUnits() {
        int locationUnits = 20;
        int units = 50;
        inventory.setLocation(location);
        when(location.getTotalUnits()).thenReturn(locationUnits);
        inventory.addUnits(units);
        assertEquals(units, inventory.getUnits());
    }


    @Test
    void inventoryDoesNotStoreWhenLocationIsAlreadyFUll() {
        inventory.setLocation(location);
        when(location.getTotalUnits()).thenReturn(Location.MAX_UNITS);

        assertThrows(LocationFullException.class, () -> {
            inventory.addUnits(1);

        });

    }

}
