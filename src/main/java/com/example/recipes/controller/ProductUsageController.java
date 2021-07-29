package com.example.recipes.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import com.example.recipes.model.Product;
import com.example.recipes.model.ProductUsage;
import com.example.recipes.model.Recipe;
import com.example.recipes.model.Uom;
import com.example.recipes.repository.ProductRepository;
import com.example.recipes.repository.ProductUsageRepository;
import com.example.recipes.repository.RecipeRepository;
import com.example.recipes.repository.UomRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class ProductUsageController {
	
	@Autowired
	ProductUsageRepository prodUsageRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	UomRepository uomRepository;
	
	@Autowired
	RecipeRepository recipeRepository;
	
	@GetMapping("/product-usage")
	public ResponseEntity<List<ProductUsage>> getAllProductUsage () {
		try {
			List<ProductUsage> prodUsage = new ArrayList<>();
			
			prodUsageRepository.findAll().forEach(prodUsage::add);
			
			
			if (prodUsage.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(prodUsage, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/product-usage")
	public ResponseEntity<ProductUsage> createProductUsage (@RequestBody ProductUsage prodUsage) {
		try {
			Product product = productRepository.findByName(prodUsage.getProduct().getName());
			Uom uom = uomRepository.findByName(prodUsage.getProductUOM().getUomName());
			Recipe recipe = recipeRepository.findByName(prodUsage.getRecipe().getName());
			
			if (product == null) {
				product = new Product(prodUsage.getProduct().getName());
				productRepository.save(product);
			}
			
			if (uom == null) {
				uom = new Uom(prodUsage.getProductUOM().getUomName());
				uomRepository.save(uom);
			}
			
			if (recipe == null) {
				return new ResponseEntity<>(prodUsage, HttpStatus.BAD_REQUEST);
			}
			
			ProductUsage _prodUsage = prodUsageRepository
					.save(new ProductUsage(product,
										   prodUsage.getProductQuantity(),
										   uom,
										   recipe));
			
			
			return new ResponseEntity<>(_prodUsage,  HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/product-usage/{id}")
	public ResponseEntity<ProductUsage> updateProductUsage (@PathVariable("id") long id, @RequestBody ProductUsage prodUsage) {
			Optional<ProductUsage> productUsageData = prodUsageRepository.findById(id);
			
			if (productUsageData.isPresent()) {
				ProductUsage _prodUsage = productUsageData.get();
				_prodUsage.setProduct(prodUsage.getProduct());
				_prodUsage.setProductQuantity(prodUsage.getProductQuantity());
				_prodUsage.setProductUOM(prodUsage.getProductUOM());
				
				return new ResponseEntity<>(prodUsageRepository.save(_prodUsage), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
	}

	@DeleteMapping("/product-usage/{id}")
	public ResponseEntity<ProductUsage> deleteProductUsage (@PathVariable("id") long id) {
		try {
			prodUsageRepository.deleteById(id);
			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/product-usage")
	public ResponseEntity<HttpStatus> deleteAllProdUs () {
		try {
			prodUsageRepository.deleteAll();
			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	

}
