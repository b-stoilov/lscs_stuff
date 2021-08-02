package com.example.recipes.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.recipes.helpclasses.RecipeStepId;
import com.example.recipes.model.EquipmentUsage;
import com.example.recipes.model.ProductUsage;
import com.example.recipes.model.Recipe;
import com.example.recipes.model.RecipeStep;
import com.example.recipes.repository.EquipmentUsageRepository;
import com.example.recipes.repository.ProductUsageRepository;
import com.example.recipes.repository.RecipeRepository;
import com.example.recipes.repository.RecipeStepRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class RecipeStepController {
	
	@Autowired
	RecipeStepRepository recipeStepRepository;
	
	@Autowired
	RecipeRepository recipeRepository;
	
	@Autowired
	EquipmentUsageRepository equipmentUsageRepository;
	
	@Autowired
	ProductUsageRepository productUsageRepository;
	
	@GetMapping("/recipe-step")
	public ResponseEntity<List<RecipeStep>> getAllRecipeSteps () {
		try {
			List<RecipeStep> steps = new ArrayList<>();
			
			recipeStepRepository.findAll().forEach(steps::add);
			
			if (steps.isEmpty()) {
				return new ResponseEntity<>(steps, HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(steps, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("/recipe-step")
	public ResponseEntity<RecipeStep> addRecipeStep (@RequestBody RecipeStep recipeStep) {
		try {
			Recipe recipe = recipeRepository.findById(recipeStep.getTempId());
			long recipeId = recipeStep.getTempId();
			long sequence = recipeStepRepository.findByRecipeId(recipeStep.getTempId()).size();
			
			EquipmentUsage equipmentUsage = equipmentUsageRepository.findById(recipeStep.getEqUsId());
//			
//			List<ProductUsage> prodUsages = productUsageRepository.findByProductUsageIds(recipeStep.getProdUsIds());
			
			if (recipe != null) {
				RecipeStep recipeStepA = new RecipeStep(recipe, recipeStep.getName(), equipmentUsage);

				recipeStepA.setRecipeStepId(new RecipeStepId(recipeId, sequence + 1));
				
				
				recipeStepRepository.save(recipeStepA);
				
				return new ResponseEntity<>(recipeStepA, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/recipe-step")
	public ResponseEntity<HttpStatus> deleteAllSteps () {
		try {
			recipeStepRepository.deleteAll();
			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
