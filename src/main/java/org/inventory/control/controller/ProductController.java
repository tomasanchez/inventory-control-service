package org.inventory.control.controller;

import java.util.Map;
import com.google.gson.Gson;
import org.inventory.control.model.product.Product;
import org.inventory.control.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;


    @PostMapping(path = "/products", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody String onAddProduct(
            @RequestParam(required = false) Map<String, String> allParams) {

        Product product = new Product().setName(allParams.get("name"))
                .setDescription(allParams.get("description"))
                .setPrice(Double.parseDouble(allParams.get("price")))
                .setCode(allParams.get("code"));

        productRepository.save(product);

        return new Gson().toJson(product);
    }

    @GetMapping(path = "/products")
    public @ResponseBody Iterable<Product> onGetProducts() {
        return productRepository.findAll();
    }



}
