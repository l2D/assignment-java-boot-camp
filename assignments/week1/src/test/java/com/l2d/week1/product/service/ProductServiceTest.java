package com.l2d.week1.product.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.l2d.week1.product.model.Product;
import com.l2d.week1.product.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Test
    @DisplayName("""
            Should return product detail
            """)
    void getProductById() {
        // Arrange
        Product product = new Product();
        product.setName("Product 1");
        product.setPrice(new BigDecimal(Math.round(Math.random() * 9000 + 100)));
        product.setDescription("Description 1");
        product.setId(1);
        product.setStock((int) Math.round(Math.random() * 100));

        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        // Act
        ProductService productService = new ProductService();
        productService.setProductRepository(productRepository);
        Optional<Product> result = productService.getProductById(1);

        // Assert
        assertNotNull(result);
    }

    @Test
    @DisplayName("""
            Should return product list if product exists
            """)
    void searchProduct() {
        // Arrange
        Product product = new Product();
        product.setName("Product 1");
        product.setPrice(new BigDecimal(Math.round(Math.random() * 9000 + 100)));
        product.setDescription("Description 1");
        product.setId(1);
        product.setStock((int) Math.round(Math.random() * 100));

        when(productRepository.findByNameContainingIgnoreCase("Product 1")).thenReturn(Optional.of(List.of(product)));

        // Act
        ProductService productService = new ProductService();
        productService.setProductRepository(productRepository);
        Optional<List<Product>> result = productService.searchProduct("Product 1");

        // Assert
        assertNotNull(result);
    }

    @Test
    @DisplayName("""
            Should return product list
            """)
    void getProducts() {
        // Arrange
        Product product = new Product();
        product.setName("Product 1");
        product.setPrice(new BigDecimal(Math.round(Math.random() * 9000 + 100)));
        product.setDescription("Description 1");
        product.setId(1);
        product.setStock((int) Math.round(Math.random() * 100));

        when(productRepository.findAll()).thenReturn(List.of(product));

        // Act
        ProductService productService = new ProductService();
        productService.setProductRepository(productRepository);
        List<Product> result = productService.getProducts();

        // Assert
        assertNotNull(result);
    }
}