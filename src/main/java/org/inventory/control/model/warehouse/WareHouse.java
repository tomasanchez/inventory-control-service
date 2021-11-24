package org.inventory.control.model.warehouse;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import org.inventory.core.database.PersistentEntity;

@Entity
public class WareHouse extends PersistentEntity {


    @Column(unique = true, nullable = false, length = 4)
    private String code;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Location> locations = new ArrayList<>();

    public List<Location> getLocations() {
        return locations;
    }

    public String getCode() {
        return code;
    }

    public WareHouse setCode(String code) {
        this.code = code;
        return this;
    }

}
