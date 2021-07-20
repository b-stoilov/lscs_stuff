package com.example.recipes.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.recipes.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	List<Product> findByNameContaining(String name);
}
