package com.example.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.recipes.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
	Recipe findByName (String name);
	Recipe findById (long id);

}
