package com.example.recipes.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.recipes.model.Equipment;
import com.example.recipes.model.EquipmentUsage;
import com.example.recipes.model.Recipe;
import com.example.recipes.model.RecipeStep;
import com.example.recipes.repository.EquipmentRepository;
import com.example.recipes.repository.EquipmentUsageRepository;
import com.example.recipes.repository.RecipeRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class EquipmentUsageController {
	
	@Autowired
	EquipmentUsageRepository eqUsageRepository;
	
	@Autowired
	EquipmentRepository eqRepository;
	
	@Autowired
	RecipeRepository recipeRepository;
	
	@GetMapping("/equipment-usage")
	public ResponseEntity<List<EquipmentUsage>> getAllEqUsages () {
		try {
			List<EquipmentUsage> eqUsages = new ArrayList<>();
			
			eqUsageRepository.findAll().forEach(eqUsages::add);
		
		
			if (eqUsages.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(eqUsages, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/equipment-usage/{id}")
	public ResponseEntity<List<EquipmentUsage>> getEquipmentUsageByRecipeId (@PathVariable long id) {
		try {
			List<EquipmentUsage> equipmentUsages = eqUsageRepository.findAll();
			List<EquipmentUsage> wantedEqUsages = new ArrayList<>();
			
			for (EquipmentUsage eqUsage : equipmentUsages) {
				if (eqUsage.getRecipe().getId() == id) {
					wantedEqUsages.add(eqUsage);
				}
			}
			
			if (wantedEqUsages.isEmpty()) {
				return new ResponseEntity<>(wantedEqUsages, HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<> (wantedEqUsages, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@PostMapping("/equipment-usage")
	public ResponseEntity<EquipmentUsage> addUsage (@RequestBody EquipmentUsage equipmentUsage) {
		try {
			Equipment equipment = eqRepository.findByName(equipmentUsage.getEquipment().getEquipmntName());
			Recipe recipe = recipeRepository.findById(equipmentUsage.getRecipeIdd());
			
			if (equipment == null) {
				equipment = new Equipment(equipmentUsage.getEquipment().getEquipmntName());
				eqRepository.saveAndFlush(equipment);
			}
			
			if (recipe == null) {
				return new ResponseEntity<>(equipmentUsage, HttpStatus.BAD_REQUEST);
			}
			
			EquipmentUsage _eqUsage = eqUsageRepository
					.saveAndFlush(new EquipmentUsage(equipment, recipe));
			
			return new ResponseEntity<>(_eqUsage, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/equipment-usage")
	public ResponseEntity<HttpStatus> deleteAllUsages () {
		try {
			eqUsageRepository.deleteAll();
			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
