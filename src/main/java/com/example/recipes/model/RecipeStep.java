package com.example.recipes.model;

import java.util.List;
import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	
	@Transient
	private long eqUsId;
	
	@Transient 
	private List<Long> prodUsIds;
	
	@Column(name = "seq_name")
	private String name;
	
//	@OneToOne
//	@JoinColumn(name="eu_id", referencedColumnName = "id")
//	private EquipmentUsage equipmentUsage;
//	
//	@OneToMany 
//	@JoinColumn(name = "pu_id", referencedColumnName = "id")
//	private List<ProductUsage> productUsages;

	public RecipeStep () {
	}
	
	public RecipeStep(Recipe recipe, String name) {
		this.id = recipe;
		this.name = name;
//		this.equipmentUsage = equipmentUsage;
//		this.productUsages = productUsages;
	}
	
	
	public RecipeStep(long tempId, long eqUsId, List<Long> prodUsIds, String name) {
		this.tempId = tempId;
		this.name = name;
		this.eqUsId = eqUsId;
		this.prodUsIds = prodUsIds;
	}

	public RecipeStepId getRecipeStepId() {
		return recipeStepId;
	}
	

	public void setRecipeStepId(RecipeStepId recipeStepId) {
		this.recipeStepId = recipeStepId;
	}
	
	public long getEqUsId() {
		return eqUsId;
	}

	public void setEqUsId(long eqUsId) {
		this.eqUsId = eqUsId;
	}

	public List<Long> getProdUsIds() {
		return prodUsIds;
	}

	public void setProdUsIds(List<Long> prodUsIds) {
		this.prodUsIds = prodUsIds;
	}

//	public EquipmentUsage getEquipmentUsage() {
//		return equipmentUsage;
//	}
//
//	public void setEquipmentUsage(EquipmentUsage equipmentUsage) {
//		this.equipmentUsage = equipmentUsage;
//	}

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
