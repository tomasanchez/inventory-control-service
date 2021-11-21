package org.inventory.control.model.product;

public class Shipping {

    private String mode;
    private boolean localPickUp;
    private boolean storePickUp;
    private String type;

    public String getMode() {
        return mode;
    }

    public Shipping setMode(String mode) {
        this.mode = mode;
        return this;
    }

    public boolean isLocalPickUp() {
        return localPickUp;
    }

    public Shipping setLocalPickUp(boolean localPickUp) {
        this.localPickUp = localPickUp;
        return this;
    }

    public boolean isStorePickUp() {
        return storePickUp;
    }

    public Shipping setStorePickUp(boolean storePickUp) {
        this.storePickUp = storePickUp;
        return this;
    }

    public String getType() {
        return type;
    }

    public Shipping setType(String type) {
        this.type = type;
        return this;
    }

    public boolean isInWarehouse() {
        try {
            return "fullfillment".equals(this.getType());
        } catch (Exception e) {
            return false;
        }
    }


}
