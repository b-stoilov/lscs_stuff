package com.example.recipes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.recipes.model.RecipeStep;

public interface RecipeStepRepository extends JpaRepository<RecipeStep, Long>{
	RecipeStep findByName (String name);
	List<RecipeStep> findById (long id);

}
