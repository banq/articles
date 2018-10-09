package com.example.product.demoproduct.service;

import com.example.product.demoproduct.domain.Product;

import java.util.Collection;

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

	/**
	 * @param productId
	 */
	public void deleteProduct(String productId);

	Collection<Product> getProducts();

}