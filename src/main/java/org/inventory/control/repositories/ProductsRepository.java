package org.inventory.control.repositories;

import org.inventory.control.model.product.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductsRepository extends CrudRepository<Product, Long> {
    @Query(value = "SELECT * FROM Product WHERE code = ?1", nativeQuery = true)
    Product findByCode(String code);
}
