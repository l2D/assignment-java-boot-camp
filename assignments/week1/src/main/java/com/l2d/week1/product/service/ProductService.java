package com.l2d.week1.product.service;

import java.util.List;
import java.util.Optional;

import com.l2d.week1.product.model.Product;
import com.l2d.week1.product.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    };

    public Optional<Product> getProductById(Integer productUid) {

        return productRepository.findById(productUid);
    }

    public Optional<List<Product>> searchProduct(String name) {

        return productRepository.findByNameContainingIgnoreCase(name);
    }

    // Note: Add limit, offet and sort for better query performance
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}
