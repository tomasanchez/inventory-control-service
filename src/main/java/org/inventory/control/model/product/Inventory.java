package org.inventory.control.model.product;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.inventory.control.exceptions.LocationFullException;
import org.inventory.control.model.warehouse.Location;
import org.inventory.core.database.PersistentEntity;


@Entity
public class Inventory extends PersistentEntity {

    @ManyToOne
    private Product product;

    @ManyToOne
    private Location location;

    private int units;

    public Inventory() {}

    public Product getProduct() {
        return product;
    }

    public Inventory setProduct(Product product) {
        this.product = product;
        return this;
    }

    public Location getLocation() {
        return location;
    }

    public Inventory setLocation(Location location) {
        this.location = location;
        return this;
    }

    public int getUnits() {
        return units;
    }

    public Inventory setUnits(int units) {
        this.units = units;
        return this;
    }

    public Inventory addUnits(int units) {
        validateUnits(units);
        return this;
    }


    /**
     * Validates that unit amounts to be set does not exceed the MAX_UNITS allowed.
     * 
     * @param units the units to validate
     * @throws LocationFullException
     */
    private void validateUnits(int units) {

        int newTotal = 0;

        try {
            newTotal = getLocation().getTotalUnits() + units;
        } catch (NullPointerException e) {
            newTotal = this.units + units;
        }

        if (newTotal <= Location.MAX_UNITS) {
            this.units += units;
        } else {
            throw new LocationFullException("Location is full - cannot contain " + newTotal);
        }
    }

}
