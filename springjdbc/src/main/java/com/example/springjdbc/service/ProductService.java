package com.example.springjdbc.service;

import com.example.springjdbc.domain.Product;

/**
 * @author banq
 * @version 1.0
 * @created 09-10��-2018 11:25:12
 */

public interface ProductService {

	/**
	 * @param productId
	 */
	public Product getProduct(String productId);

	/**
	 * @param product
	 */
	public void createProduct(Product product);


}