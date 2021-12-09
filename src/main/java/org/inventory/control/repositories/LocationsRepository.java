package org.inventory.control.repositories;

import org.inventory.control.model.warehouse.Location;
import org.springframework.data.repository.CrudRepository;

public interface LocationsRepository extends CrudRepository<Location, Long> {

}
