package org.inventory.control.repositories;

import org.inventory.control.model.product.Inventory;
import org.springframework.data.repository.CrudRepository;

public interface InventoriesRepository extends CrudRepository<Inventory, Long> {

}
