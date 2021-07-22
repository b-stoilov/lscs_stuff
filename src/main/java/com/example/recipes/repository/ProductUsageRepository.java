package com.example.recipes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.recipes.model.ProductUsage;

public interface ProductUsageRepository extends JpaRepository<ProductUsage, Long> {
	List<ProductUsage> findByRecipeId (long id);
}
