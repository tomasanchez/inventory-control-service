package org.inventory.control.model.warehouse;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LocationCode {

    @Column(length = 2)
    private String area;

    @Column(length = 2)
    private String halfway;

    @Column(length = 2)
    private String face;

    @Column(length = 2)
    private String rack;


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
}
