package com.example.inventory.service;

import com.example.inventory.model.Product;
import com.example.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private ProductRepository repository;

    public Map<String, Double> getTotalValueByCategory() {
        List<Product> products = repository.findAll();
        Map<String, Double> categoryTotals = new HashMap<>();

        for (Product product : products) {
            double price = product.getPrice() * product.getQuantity();
            categoryTotals.merge(product.getCategory(), price, Double::sum);
        }
        return categoryTotals;
    }

    public Map<String, List<Product>> getDiscountedProductsByCategory() {
        List<Product> products = repository.findExpiringInNext7Days();
        for (Product product : products) {
            product.setDiscount(product.getPrice() * 0.2);
        }
        return products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));
    }
}