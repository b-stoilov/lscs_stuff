package com.example.recipes.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.recipes.model.ProductUsage;

public interface ProductUsageRepository extends JpaRepository<ProductUsage, Long> {
	List<ProductUsage> findByRecipeId (long id);
	ProductUsage findById (long id);
	
	default List<ProductUsage> findByProductUsageIds (List<Long> ids) {
		List<ProductUsage> prodUsages = new ArrayList<>();
		
		ids.forEach(id -> {
			ProductUsage prodUsage = findById(id).get();
			
			prodUsages.add(prodUsage); 
		});
		
		return prodUsages;
	}
}
