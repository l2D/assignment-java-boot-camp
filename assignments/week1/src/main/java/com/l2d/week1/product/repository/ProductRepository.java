package com.l2d.week1.product.repository;

import java.util.List;
import java.util.Optional;

import com.l2d.week1.product.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAll();

    Optional<Product> findById(Integer productId);

    Optional<List<Product>> findByNameContainingIgnoreCase(String productName);

}
