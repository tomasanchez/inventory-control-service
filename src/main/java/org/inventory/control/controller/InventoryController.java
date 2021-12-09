package org.inventory.control.controller;

import java.util.Objects;
import javax.persistence.EntityNotFoundException;
import com.google.gson.Gson;
import org.inventory.control.exceptions.InvalidLocationException;
import org.inventory.control.model.product.Inventory;
import org.inventory.control.model.product.Product;
import org.inventory.control.model.warehouse.Location;
import org.inventory.control.model.warehouse.LocationCode;
import org.inventory.control.model.warehouse.WareHouse;
import org.inventory.control.repositories.InventoriesRepository;
import org.inventory.control.repositories.LocationsRepository;
import org.inventory.control.repositories.ProductsRepository;
import org.inventory.control.repositories.WareHousesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(path = "/inventories")
public class InventoryController {

    @Autowired
    LocationsRepository locationsRepository;

    @Autowired
    WareHousesRepository warehousesRepository;

    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    InventoriesRepository inventoriesRepository;

    /**
     * Adds a product to the inventory in a warehouse,
     * 
     * @param warehouse code
     * @param product code
     * @param location pattern
     * @param units number
     * @return an Inventory
     */
    @PostMapping(path = "/", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Object onAddProduct(@RequestParam String warehouse,
            @RequestParam String product, @RequestParam String location, String units) {

        LocationCode locationCode = new LocationCode(location.toUpperCase());

        WareHouse wh = warehousesRepository.findByCode(warehouse.toUpperCase());

        if (Objects.isNull(wh)) {
            throw new EntityNotFoundException();
        }

        Product p = productsRepository.findByCode(product.toUpperCase());

        if (Objects.isNull(p)) {
            throw new EntityNotFoundException();
        }


        Location lct = wh.getLocations().stream()
                .filter(l -> l.getCode().toString().equals(locationCode.toString())).findFirst()
                .orElseThrow(InvalidLocationException::new);

        Inventory inventory = new Inventory();

        inventory.setProduct(p).setLocation(lct).addUnits(Integer.parseInt(units));

        return new Gson().toJson(inventoriesRepository.save(inventory));
    }

}
