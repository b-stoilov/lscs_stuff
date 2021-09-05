package com.example.recipes.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.recipes.helpclasses.RecipeStepId;
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
	
	@Query(value = "SELECT Equipment.name FROM RecipeStep INNER JOIN EquipmentUsage ON RecipeStep.eu_id = EquipmentUsage.id INNER JOIN Equipment ON EquipmentUsage.e_id = Equipment.id WHERE recipestep.r_id = :r_id AND recipestep.seq = :seq;", nativeQuery = true)
	String getEquipmentName (@Param("r_id") long r_id, @Param("seq") long seq);
	
	@Query(value = "SELECT * FROM RecipeStep WHERE r_id = :r_id AND seq = :seq", nativeQuery = true)
	RecipeStep getRecipeStepByRecipeStepId (@Param("r_id") long r_id, @Param("seq") int seq);

}
