package com.example.product.demoproduct.service.impl;

import com.example.product.demoproduct.domain.Product;
import com.example.product.demoproduct.repository.ProductRepo;
import com.example.product.demoproduct.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo productRepo;

	@Override
	public Product getProduct(String productId) {
		return productRepo.findById(productId).orElse(new Product());
	}

	@Override
	public void createProduct(Product product) {
		productRepo.save(product);
	}

	@Override
	public void deleteProduct(String productId) {
		Product product = getProduct(productId);
		productRepo.delete(product);
	}

	public Collection<Product> getProducts() {
		return productRepo.findAll();
	}
}
