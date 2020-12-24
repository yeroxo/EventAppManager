package com.example.productList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class ProductListApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductListApplication.class, args);
	}

}
