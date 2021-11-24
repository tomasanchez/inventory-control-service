package org.inventory.control.repositories;

import org.inventory.control.model.warehouse.WareHouse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface WareHousesRepository extends CrudRepository<WareHouse, Long> {
    @Query(value = "SELECT * FROM ware_house WHERE code = ?1", nativeQuery = true)
    WareHouse findByCode(String code);
}
