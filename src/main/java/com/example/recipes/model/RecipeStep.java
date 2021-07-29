package com.example.recipes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

import com.example.recipes.helpclasses.RecipeStepId;


@Entity
@Table(name = "recipestep")
@IdClass(RecipeStepId.class)
public class RecipeStep {
	
	@Id
	@ManyToOne
	@JoinColumn(name = "r_id", referencedColumnName = "id")
	private Recipe recipe;

	@Id
	private long sequence;
	
	@Column(name = "seq_name")
	private String name;

	public RecipeStep () {
	}
	
	public RecipeStep(Recipe recipe, String name) {
		this.recipe = recipe;
		this.name = name;
	}
	

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public long getSequence() {
		return sequence;
	}

	public void setSequence(long sequence) {
		this.sequence = sequence;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	

}
