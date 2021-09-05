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
import org.springframework.web.bind.annotation.RequestParam;
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
			List<RecipeStep> steps = recipeStepRepository.findAll();
			
			if (steps.isEmpty()) {
				return new ResponseEntity<>(steps, HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(steps, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/recipe-step/{id}")
	public ResponseEntity<List<RecipeStep>> getRecipeStepsByRecipeId (@PathVariable long id) {
		try {
			List<RecipeStep> recipeSteps = recipeStepRepository.findAll();
			List<RecipeStep> wantedRecipeSteps = new ArrayList<>();
			
			for (RecipeStep recipeStep : recipeSteps) {
				if (recipeStep.getRecipeStepId().getId() == id) {
					wantedRecipeSteps.add(recipeStep);
				}
			}
			
			if (wantedRecipeSteps.isEmpty()) {
				return new ResponseEntity<>(wantedRecipeSteps, HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<> (wantedRecipeSteps, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
//	@GetMapping("/recipe-step?r_id={id}&seq={seq}")
//	public ResponseEntity<RecipeStep> getEquipmentNameByRecipeStepId (@RequestParam("r_id") long r_id,
//			@RequestParam("seq") int seq) {
//		try {
//			RecipeStep recipeStep = recipeStepRepository.getRecipeStepByRecipeStepId(r_id, seq);
//			
//			return new ResponseEntity<> (recipeStep, HttpStatus.OK);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	
	@GetMapping("/recipe-step?r_id={id}&seq={seq}")
	public ResponseEntity<String> getEquipmentNameByRecipeStepId (@RequestParam("r_id") long r_id,
			@RequestParam("seq") long seq) {
		try {
			String eqName = recipeStepRepository.getEquipmentName(r_id, seq);
			
			return new ResponseEntity<> (eqName, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
			
			List<ProductUsage> prodUsages = productUsageRepository.findByProductUsageIds(recipeStep.getProdUsIds());
			
			if (recipe != null) {
				RecipeStep recipeStepA = new RecipeStep(recipe, recipeStep.getName(), equipmentUsage);

				recipeStepA.setRecipeStepId(new RecipeStepId(recipeId, sequence + 1));
				recipeStepA.setUsedProducts(prodUsages);
				
				
				recipeStepRepository.saveAndFlush(recipeStepA);
	
				
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
