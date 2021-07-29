package com.example.recipes.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "recipe")
public class Recipe {

	@Id
	@GeneratedValue(generator = "recipe_seq_gen")
	private long id;
	
	@Column(name = "r_name")
	private String name;
	
	@Column(name = "r_description")
	private String desc;
	
	@Column(name = "user_id")
	private long userId;
	
	@OneToMany(mappedBy="recipe", cascade = CascadeType.ALL)
	private Set<RecipeStep> recipeSteps;
	
	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
	private Set<ProductUsage> productsUsages;
	
	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
	private Set<EquipmentUsage> equipmentUsages;

	public Recipe() {}

	public Recipe(String name, String desc, long userId) {
		this.name = name;
		this.desc = desc;
		this.userId = userId;
	}
	

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	
	
	
	
}
