package com.example.inventory.service;

import com.example.inventory.model.Product;
import com.example.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public void addProduct(Product product) {
        repository.save(product);
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public List<Product> getExpiringProducts() {
        List<Product> products = repository.findExpiringInNext7Days();
        for (Product product : products) {
            double discount = product.getPrice() * 0.2;
            product.setDiscount(discount);
        }
        return products;
    }

    public List<Product> getExpiredProducts() {
        return repository.findExpired();
    }
}