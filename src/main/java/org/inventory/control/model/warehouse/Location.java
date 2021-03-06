package org.inventory.control.model.warehouse;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import org.inventory.control.exceptions.LocationFullException;
import org.inventory.control.model.product.Inventory;
import org.inventory.core.database.PersistentEntity;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"area", "halfway", "rack", "face", "warehouse"})})
public class Location extends PersistentEntity {

    @Transient
    public static final int MAX_UNITS = 100;

    @Transient
    public static final int MAX_PRODUCTS = 3;

    @Embedded
    public LocationCode code;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "warehouse")
    private WareHouse warehouse;


    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Inventory> inventories = new ArrayList<>();

    public Location() {}

    public LocationCode getCode() {
        return this.code;
    }

    public Location setCode(LocationCode code) {
        this.code = code;
        return this;
    }

    public WareHouse getWarehouse() {
        return warehouse;
    }

    public Location setWarehouse(WareHouse warehouse) {
        this.warehouse = warehouse;
        return this;
    }

    public List<Inventory> getInventories() {
        return this.inventories;
    }

    /**
     * Count the units in this location.
     * 
     * @return the sum of units
     */
    public int getTotalUnits() {
        return getInventories().stream().mapToInt(Inventory::getUnits).sum();
    }

    /**
     * Adds an inventory entry.
     * 
     * @param inventory a new inventory entry
     * @return the location
     * @throws LocationFullException
     */
    public Location addInventory(Inventory inventory) {

        if (getInventories().size() < MAX_PRODUCTS) {
            inventories.add(inventory);
        }

        try {
            Inventory i = getInventory(inventory);
            i.addUnits(inventory.getUnits());
        } catch (NoSuchElementException e) {
            throw new LocationFullException("Cannot add another product, location already contains "
                    + MAX_PRODUCTS + " products");
        }


        return this;
    }

    /**
     * Provides the inventory of a product.
     * 
     * @param inventory and inventory to be checked.
     * @return
     */
    private Inventory getInventory(Inventory inventory) {
        return getInventories().stream()
                .filter(i -> i.getProduct().getCode().equals(inventory.getProduct().getCode()))
                .findFirst().orElseThrow();
    }

}
