package com.l2d.week1;

import java.math.BigDecimal;
import java.security.SecureRandom;

import javax.annotation.PostConstruct;

import com.l2d.week1.product.model.Product;
import com.l2d.week1.product.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.l2d.week1.product.repository.ProductRepository.*;

@SpringBootApplication
public class Week1Application {

	@Autowired
	private ProductRepository productRepository;

	@PostConstruct
	public void initialData() {
		// Loop 10 times to generate products
		SecureRandom randomPrice = new SecureRandom();
		for (int i = 1; i <= 10; i++) {
			Product product = new Product();
			product.setName("Product " + i);
			product.setPrice(new BigDecimal(Math.round(randomPrice.nextInt(9000))));
			product.setDescription("Description " + i);
			product.setId(i);
			product.setStock((int) Math.round(randomPrice.nextInt(9000)));
			productRepository.save(product);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(Week1Application.class, args);
	}

}
