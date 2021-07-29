package com.example.recipes.helpclasses;

import java.io.Serializable;

import javax.persistence.Embeddable;

import com.example.recipes.model.Recipe;

@Embeddable
public class RecipeStepId {
	private long id;

    private long sequence;

	public RecipeStepId() {}

	public RecipeStepId(long id, long sequence) {
		this.id = id;
		this.sequence = sequence;
	}

	

    

    
}
