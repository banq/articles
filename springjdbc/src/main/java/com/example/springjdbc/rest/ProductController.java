package com.example.springjdbc.rest;

import com.example.springjdbc.domain.Product;
import com.example.springjdbc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;

@RestController
public class ProductController {
	@Autowired
	private ProductService productService;

	@PostMapping("/product")
	public void createProduct(@RequestBody Product product) {
		productService.createProduct(product);
	}

	@PostMapping("/product2")
	public void createProduct2(@RequestParam String productId) {
		System.out.println("hello " + productId);
	}

	@GetMapping("/product")
	@ResponseStatus(HttpStatus.FOUND)
	public Product getProduct(@RequestParam String productId) {
		return productService.getProduct(productId);
	}

	@GetMapping("/products")
	public Collection<Product> getProducts() {
		Collection<Product> cltn = new ArrayList();
		Iterable<Product> ps = productService.getProducts();
		for (Product customer : ps)
			cltn.add(customer);
		return cltn;
	}

	@GetMapping("/products/count")
	public long getProductCount() {
		return productService.getCount();
	}
}
