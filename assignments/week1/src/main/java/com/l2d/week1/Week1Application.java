package com.l2d.week1;

import java.math.BigDecimal;
import java.security.SecureRandom;

import javax.annotation.PostConstruct;

import com.l2d.week1.product.model.Product;
import com.l2d.week1.product.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Week1Application {

	@Autowired
	private ProductRepository productRepository;

	@PostConstruct
	public void initialData() {
		// Loop 10 times to generate products
		SecureRandom randomNumber = new SecureRandom();
		for (int i = 1; i <= 10; i++) {
			Product product = new Product();
			product.setName("Product " + i);
			product.setPrice(new BigDecimal(randomNumber.nextInt(9000)));
			product.setDescription("Description " + i);
			product.setId(i);
			product.setStock(randomNumber.nextInt(1000));
			productRepository.save(product);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(Week1Application.class, args);
	}

}
