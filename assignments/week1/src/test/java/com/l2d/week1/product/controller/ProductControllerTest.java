package com.l2d.week1.product.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

import com.l2d.week1.product.model.Product;
import com.l2d.week1.product.repository.ProductRepository;
import com.l2d.week1.product.utils.ProductResponse;

import org.junit.jupiter.api.DisplayName;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private ProductRepository productRepository;

    private SecureRandom randomNumber = new SecureRandom();

    @Test
    @DisplayName("""
            Should return product list
            """)
    void getProductsListOrByName() {
        Product product = new Product();
        product.setName("Product 1");
        product.setPrice(new BigDecimal(randomNumber.nextInt(9000)));
        product.setDescription("Description 1");
        product.setId(1);
        product.setStock(randomNumber.nextInt(1000));

        when(productRepository.findByNameContainingIgnoreCase("Product 1")).thenReturn(Optional.of(List.of(product)));

        // Act

        ProductResponse productResponse = testRestTemplate.getForObject("/products?search=Product 1",
                ProductResponse.class);

        List<Product> bodyProductResponse = (List<Product>) productResponse.getData();

        // Assert
        assertEquals(200, productResponse.getStatus());
        assertEquals(1, bodyProductResponse.size());
    }

    @Test
    @DisplayName("""
            Should return product by id
            """)
    void getProductById() {
        // Arrange
        Product product = new Product();
        product.setName("Product 1");
        product.setPrice(new BigDecimal(randomNumber.nextInt(9000)));
        product.setDescription("Description 1");
        product.setId(1);
        product.setStock(randomNumber.nextInt(1000));

        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        // Act
        ProductResponse productResponse = testRestTemplate.getForObject("/products/1", ProductResponse.class);

        Object bodyProductResponse = productResponse.getData();

        // Assert
        assertEquals(200, productResponse.getStatus());
        assertNotNull(bodyProductResponse);
    }
}