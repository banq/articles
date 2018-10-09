package com.example.product.demoproduct.rest;

import com.example.product.demoproduct.domain.Product;
import com.example.product.demoproduct.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ProductController {
	@Autowired
	private ProductService productService;

	@PostMapping("/product")
	public void createProduct(@RequestBody Product product) {
		productService.createProduct(product);
	}

	@GetMapping("/product")
	@ResponseStatus(HttpStatus.FOUND)
	public Product getProduct(@RequestParam String productId) {
		return productService.getProduct(productId);
	}

	@GetMapping("/products")
	public Collection<Product> getProducts() {
		return productService.getProducts();
	}
}
