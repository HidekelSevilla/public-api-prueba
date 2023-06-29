package com.springproducts.springproductsproof;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@ComponentScan(basePackages = {"com.springproducts.springproductsproof","com.springproducts.controllers","com.springproducts.services","com.springproducts.repository","com.springproducts.model"})
public class SpringProductsProofApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringProductsProofApplication.class, args);
	}
}

