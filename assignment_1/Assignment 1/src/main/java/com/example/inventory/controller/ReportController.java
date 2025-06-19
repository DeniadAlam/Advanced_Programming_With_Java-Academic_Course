package com.example.inventory.controller;

import com.example.inventory.model.Product;
import com.example.inventory.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/value-by-category")
    public Map<String, Double> getTotalValueByCategory() {
        return reportService.getTotalValueByCategory();
    }

    @GetMapping("/discounted-products")
    public Map<String, List<Product>> getDiscountedProductsByCategory() {
        return reportService.getDiscountedProductsByCategory();
    }
}