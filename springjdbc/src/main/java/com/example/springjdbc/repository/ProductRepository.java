package com.example.springjdbc.repository;

import com.example.springjdbc.domain.Product;
import org.springframework.data.repository.CrudRepository;


public interface ProductRepository extends CrudRepository<Product, String> {

}





