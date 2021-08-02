package com.example.recipes.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

import com.example.recipes.helpclasses.RecipeStepId;


@Entity
@Table(name = "recipestep")
public class RecipeStep {
	
	@EmbeddedId
	private RecipeStepId recipeStepId;
	
	@ManyToOne
	@JoinColumn(name = "r_id", referencedColumnName = "id")
	@MapsId("id")
	private Recipe id;
	
	@Transient
	private long tempId;
	
	@Column(name = "seq_name")
	private String name;

	public RecipeStep () {
	}
	
	public RecipeStep(Recipe recipe, String name) {
		this.id = recipe;
		this.name = name;
	}
	
	
	public RecipeStep(long tempId, String name) {
		this.tempId = tempId;
		this.name = name;
	}

	public RecipeStepId getRecipeStepId() {
		return recipeStepId;
	}
	
	

	public void setRecipeStepId(RecipeStepId recipeStepId) {
		this.recipeStepId = recipeStepId;
	}

	public Recipe getRecipe() {
		return id;
	}
	
	public long getTempId () {
		return tempId;
	}

	public void setRecipe(Recipe recipe) {
		this.id = recipe;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	

}
