package org.inventory.control.model.product;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import org.inventory.core.database.PersistentEntity;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "Constraint_unique_code",
        columnNames = {"code"}))
public class Product extends PersistentEntity {

    private String code;
    private String name;
    private String description;
    private String imageUrl;
    @Transient
    private int stock;
    private Double price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Inventory> inventories = new ArrayList<>();

    public Product() {}

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Product setPrice(Double price) {
        this.price = price;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Product setCode(String code) {
        this.code = code;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Product setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public List<Inventory> getInventories() {
        return inventories;
    }

    public Product addToInventory(Inventory inventory) {
        inventory.setProduct(this);
        getInventories().add(inventory);
        return this;
    }

    public int getStock() {
        this.stock = getInventories().stream().mapToInt(Inventory::getUnits).sum();
        return this.stock;
    }

}
