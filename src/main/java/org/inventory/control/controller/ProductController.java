package org.inventory.control.controller;

import org.inventory.control.model.product.Product;
import org.inventory.control.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;


    @PostMapping(path = "/products")
    public @ResponseBody String onAddProduct(@RequestParam String name,
            @RequestParam String description, @RequestParam String price) {
        Product product = new Product().setName(name).setDescription(description)
                .setPrice(Double.parseDouble(price));
        productRepository.save(product);
        return "Saved";
    }

    @GetMapping(path = "/products")
    public @ResponseBody Iterable<Product> onGetProducts() {
        return productRepository.findAll();
    }



}
