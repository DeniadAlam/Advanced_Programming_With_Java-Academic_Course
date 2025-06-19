package com.example.inventory.repository;

import com.example.inventory.model.Product;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class InMemoryProductRepository implements ProductRepository {
    private final Map<Integer, Product> productMap = new HashMap<>();

    @Override
    public void save(Product product) {
        productMap.put(product.getId(), product);
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    @Override
    public List<Product> findExpiringInNext7Days() {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        Date sevenDaysLater = calendar.getTime();

        return productMap.values().stream()
                .filter(p -> p.getExpiryDate() != null &&
                        p.getExpiryDate().after(now) &&
                        p.getExpiryDate().before(sevenDaysLater))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findExpired() {
        Date now = new Date();
        return productMap.values().stream()
                .filter(p -> p.getExpiryDate() != null && p.getExpiryDate().before(now))
                .collect(Collectors.toList());
    }
}