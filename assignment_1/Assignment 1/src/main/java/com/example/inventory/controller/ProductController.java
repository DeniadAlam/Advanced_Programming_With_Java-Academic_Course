package com.example.inventory.controller;

import com.example.inventory.model.Product;
import com.example.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public String addProduct(@RequestBody Product product) {
        service.addProduct(product);
        return "Product added successfully.";
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/expiring")
    public List<Product> getExpiringProducts() {
        return service.getExpiringProducts();
    }

    @GetMapping("/expired")
    public List<Product> getExpiredProducts() {
        return service.getExpiredProducts();
    }
}