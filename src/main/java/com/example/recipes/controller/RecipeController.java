package com.example.recipes.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.recipes.model.Recipe;
import com.example.recipes.repository.RecipeRepository;

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

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class RecipeController {

	@Autowired
	RecipeRepository recipeRepository;
	
	@GetMapping("/recipes")
	public ResponseEntity<List<Recipe>> getAllRecipes () {
		try {
			List<Recipe> recipes = recipeRepository.findAll();
			
			ResponseEntity<List<Recipe>> resp = 
					recipes.isEmpty() ? new ResponseEntity<>(null, HttpStatus.NO_CONTENT) : new ResponseEntity<>(recipes, HttpStatus.OK);
			
			return resp;
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@PostMapping("/recipes")
	public ResponseEntity<Recipe> addRecipes (@RequestBody Recipe recipe) {
		try {
			if (recipe == null || recipe.getName().isEmpty()) {
				return new ResponseEntity<>(recipe, HttpStatus.CONFLICT);
			} 
			
			recipeRepository.save(recipe);
			
			return new ResponseEntity<>(recipe, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/recipes")
	public ResponseEntity<HttpStatus> deleteAllRecipes () {
		try {
			recipeRepository.deleteAll();
			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
