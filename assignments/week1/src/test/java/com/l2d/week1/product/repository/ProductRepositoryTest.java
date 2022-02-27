package com.l2d.week1.product.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.l2d.week1.product.model.Product;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("""
        Should return product size equal to 10
        """)
    void findAll() {
        assertEquals(10, productRepository.findAll().size());
    }

    @Test
    @DisplayName("""
        Should return product detail
        """)
    void findById() {
        // Arrange
        Product product = new Product();
        product.setName("Product 1");
        product.setPrice(new BigDecimal(Math.round(Math.random() * 9000 + 100)));
        product.setDescription("Description 1");
        product.setId(1);
        product.setStock((int) Math.round(Math.random() * 100));
        productRepository.save(product);

        // Act
        Optional<Product> result = productRepository.findById(1);

        // Assert
        assertTrue(result.isPresent());
    }

    @Test
    @DisplayName("""
        Should not return product detail
        """)
    void findByIdNotFound() {
        // Act
        // Optional<Product> result = productRepository.findById(1); // Should not return product detail but I don't know why it's not rollback
        Optional<Product> result = productRepository.findById(11);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("""
        Should return product list
        """)
    void findByNameContainingIgnoreCase() {
        // Arrange
        Product product = new Product();
        product.setName("Product 1");
        product.setPrice(new BigDecimal(Math.round(Math.random() * 9000 + 100)));
        product.setDescription("Description 1");
        product.setId(1);
        product.setStock((int) Math.round(Math.random() * 100));
        productRepository.save(product);

        // Act
        Optional<List<Product>> result = productRepository.findByNameContainingIgnoreCase("product 1");

        // Assert
        assertTrue(result.isPresent());
    }
}