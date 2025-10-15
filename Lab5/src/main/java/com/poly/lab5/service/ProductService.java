package com.poly.lab5.service;

import com.poly.lab5.model.Product;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    private final List<Product> products = new ArrayList<>();

    public ProductService() {
        products.add(new Product(1L, "Laptop", 15000000));
        products.add(new Product(2L, "Phone", 8000000));
        products.add(new Product(3L, "Headphone", 1200000));
        products.add(new Product(4L, "Tablet", 6000000));
        products.add(new Product(5L, "Smartwatch", 3000000));
        products.add(new Product(6L, "Camera", 5000000));
        products.add(new Product(7L, "Speaker", 2000000));
        products.add(new Product(8L, "Monitor", 4000000));
        products.add(new Product(9L, "Keyboard", 900000));
        products.add(new Product(10L, "Mouse", 500000));
    }

    public List<Product> findAll() {
        return products;
    }

    public Product findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Product> findPage(int page, int size) {
        int start = page * size;
        int end = Math.min(start + size, products.size());
        if (start > end) return Collections.emptyList();
        return products.subList(start, end);
    }

    public int getTotalProducts() {
        return products.size();
    }

    public int getTotalPages(int size) {
        return (int) Math.ceil((double) products.size() / size);
    }
}
