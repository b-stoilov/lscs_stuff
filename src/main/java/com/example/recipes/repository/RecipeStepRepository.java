package com.example.recipes.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.recipes.model.Recipe;
import com.example.recipes.model.RecipeStep;

public interface RecipeStepRepository extends JpaRepository<RecipeStep, Long>{
	RecipeStep findByName (String name);
	List<RecipeStep> findById (long id);
	
	default List<Recipe> findByRecipeId (long id) {
		List<RecipeStep> recipeSteps = findAll();
		List<Recipe> recipes = new ArrayList<>();
		
		recipeSteps.forEach(recipeStep -> {
			if (recipeStep.getRecipeStepId().getId() == id) {
				recipes.add(recipeStep.getRecipe());
			}
		});
		
		return recipes;
	}

}
