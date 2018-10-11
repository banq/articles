package com.example.springjdbc.service.impl;

import com.example.springjdbc.domain.Product;
import com.example.springjdbc.repository.ProductRepository;
import com.example.springjdbc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;


	public Product getProduct(String productId) {
		return productRepo.findById(Integer.parseInt(productId));
	}


	public void createProduct(Product product) {
		productRepo.save(product);
	}


}
