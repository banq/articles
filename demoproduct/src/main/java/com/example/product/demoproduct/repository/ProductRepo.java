package com.example.product.demoproduct.repository;

import com.example.product.demoproduct.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, String> {
}
