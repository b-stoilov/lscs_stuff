package com.example.recipes.helpclasses;

import java.io.Serializable;

import com.example.recipes.model.Recipe;

public class RecipeStepId implements Serializable {
	private Recipe recipe;

    private long sequence;

	public RecipeStepId() {}

	public RecipeStepId(Recipe recipe, long sequence) {
		this.recipe = recipe;
		this.sequence = sequence;
	}

	

    

    
}
