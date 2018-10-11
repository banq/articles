package com.example.springjdbc.repository;

import com.example.springjdbc.domain.Product;
import org.springframework.data.repository.Repository;


public interface ProductRepository extends Repository<Product, Integer> {

	Product findById(Integer id);

	void save(Product pet);
}





