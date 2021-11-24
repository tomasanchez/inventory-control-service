package org.inventory.control.model.warehouse;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.inventory.control.exceptions.InvalidLocationException;

@Embeddable
public class LocationCode {

    @Column(length = 2)
    private String area;

    @Column(length = 2)
    private String halfway;

    @Column(length = 2)
    private String rack;

    @Column(length = 2)
    private String face;

    /**
     * Generates a Location code from a given pattern.
     * 
     * @param location pattern
     * @throws InvalidLocationException
     */
    public LocationCode(String location) {
        validateLocation(location);
        this.area = location.substring(0, 2);
        this.halfway = location.substring(3, 5);
        this.rack = location.substring(6, 8);
        this.face = location.substring(9);
    }

    public String getArea() {
        return area;
    }

    public LocationCode setArea(String area) {
        this.area = area;
        return this;
    }

    public String getHalfway() {
        return halfway;
    }

    public LocationCode setHalfway(String halfway) {
        this.halfway = halfway;
        return this;
    }

    public String getFace() {
        return face;
    }

    public LocationCode setFace(String row) {
        this.face = row;
        return this;
    }

    public String getRack() {
        return rack;
    }

    public LocationCode setRack(String rack) {
        this.rack = rack;
        return this;
    }

    /**
     * Validates that the locations follows the correct pattern.
     * 
     * @param location to be checked
     * @throws InvalidLocationException
     */
    private void validateLocation(String location) {
        if (Objects.isNull(location)
                || !location.matches("([A-Z]){2}-[0-9]{2}-[0-9]{2}-(DE|IZ)$")) {
            throw new InvalidLocationException(
                    "The location: " + location + " is not a valid location");
        }

    }

    @Override
    public String toString() {
        return String.format("%s-%s-%s-%s", getArea(), getHalfway(), getRack(), getFace());
    }
}
