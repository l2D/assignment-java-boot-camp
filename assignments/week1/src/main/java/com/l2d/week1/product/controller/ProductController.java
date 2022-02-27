package com.l2d.week1.product.controller;

import java.util.List;
import java.util.Optional;

import com.l2d.week1.product.model.Product;
import com.l2d.week1.product.service.ProductService;
import com.l2d.week1.product.utils.ProductResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ProductResponse getProductsListOrByName(
            @RequestParam(name = "search", required = false) String productName) {
        try {
            if (productName == null || productName.isEmpty()) {
                List<Product> products = productService.getProducts();
                return new ProductResponse("Success", HttpStatus.OK.value(), products);
            }
            System.out.println(productName);

            Optional<List<Product>> products = productService.searchProduct(productName);

            Boolean isEmpty = products.get().isEmpty();

            if (isEmpty) {
                return new ProductResponse("No products found", HttpStatus.NOT_FOUND.value(), null);
            }

            return new ProductResponse("Success", HttpStatus.OK.value(), products.get());
            // if (isEmpty) {
            // } else {
            //     return new ProductResponse("No products found", HttpStatus.NOT_FOUND.value(), null);
            // }
        } catch (Exception e) {
            return new ProductResponse("Error", HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
        }
    }

    @GetMapping("/{product_id}")
    public ProductResponse getProductById(@PathVariable("product_id") Integer productId) {
        try {
            Optional<Product> product = productService.getProductById(productId);
            if (product.isPresent()) {
                return new ProductResponse("Success", HttpStatus.OK.value(), product.get());
            } else {
                return new ProductResponse("No product found", HttpStatus.NOT_FOUND.value(), null);
            }
        } catch (Exception e) {
            return new ProductResponse("Error", HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
        }
    }

}