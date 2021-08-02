package com.example.recipes.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.recipes.model.ProductUsage;
import com.example.recipes.repository.ProductUsageRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="equipmentusage")
public class EquipmentUsage {
	
	@Id
	@GeneratedValue(generator = "eq_usg_gen")
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "e_id", referencedColumnName = "id")
	private Equipment equipment;
	
	@ManyToOne
	@JoinColumn(name="r_id", referencedColumnName = "id")
	private Recipe recipe;
	
	@OneToOne(mappedBy = "equipmentUsage", cascade = CascadeType.ALL)
	private RecipeStep recipeStep;
	
	@Transient
	private long recipeIdd;

	
	public EquipmentUsage() {
	}


	public EquipmentUsage(Equipment equipment, Recipe recipe) {
		this.equipment = equipment;
		this.recipe = recipe;
	}
	
	
	public EquipmentUsage(Equipment equipment, long recipeIdd) {
		this.equipment = equipment;
		this.recipeIdd = recipeIdd;
	}


	public long getId () {
		return id;
	}
	
	
	public long getRecipeIdd() {
		return recipeIdd;
	}

	public void setRecipeIdd(long recipeIdd) {
		this.recipeIdd = recipeIdd;
	}


	public Equipment getEquipment() {
		return equipment;
	}


	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}


	public Recipe getRecipe() {
		return recipe;
	}


	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}



	
	
	
	
	

}
