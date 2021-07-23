package com.example.recipes.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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

@Entity
@Table(name="equipmentusage")
public class EquipmentUsage {
	
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "p_id", referencedColumnName = "id")
	private Equipment equipment;
	
	@Column(name="r_id")
	private long recipeId;

	
	public EquipmentUsage() {
	}


	public EquipmentUsage(Equipment equipment, long recipeId) {
		this.equipment = equipment;
		this.recipeId = recipeId;
	}

	public long getId () {
		return id;
	}

	public Equipment getEquipment() {
		return equipment;
	}


	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}


	public long getRecipeId() {
		return recipeId;
	}


	public void setRecipeId(long recipeId) {
		this.recipeId = recipeId;
	}
	
	
	
	
	

}
