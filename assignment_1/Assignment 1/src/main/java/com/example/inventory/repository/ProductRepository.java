package com.example.inventory.repository;

import com.example.inventory.model.Product;
import java.util.List;

public interface ProductRepository {
    void save(Product product);
    List<Product> findAll();
    List<Product> findExpiringInNext7Days();
    List<Product> findExpired();
}