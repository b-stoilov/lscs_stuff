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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.recipes.model.Product;
import com.example.recipes.repository.ProductRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	ProductRepository productRepository;
	
	//get products
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts (@RequestParam(required = false) String name) {
		try {
			List<Product> products = new ArrayList<>();
			
			if (name == null) {
				productRepository.findAll().forEach(products::add);
			} else {
				productRepository.findByNameContaining(name).forEach(products::add);
			}
			
			if (products.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	//get products by id 
	@GetMapping("/products/{id}") 
	public ResponseEntity<Product> getProductById (@PathVariable("id") long id) {
		Optional<Product> productData = productRepository.findById(id);
		
		if (productData.isPresent()) {
			Product _product = productData.get();
			return new ResponseEntity<Product>(_product, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	//create products
	@PostMapping("/products")
	public ResponseEntity<Product> createProduct (@RequestBody Product product) {
		try {
			Product _product = productRepository
					.save(new Product(product.getName()));
			
			return new ResponseEntity<>(_product, HttpStatus.CREATED); 
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	//update product
	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
		Optional<Product> productData = productRepository.findById(id);
		
		if (productData.isPresent()) {
			Product _product = productData.get();
			_product.setName(product.getName());
			
			return new ResponseEntity<>(_product, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	//delete product by id
	@DeleteMapping("/products/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable("id") long id) {
		try {
			productRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//delete all products
	@DeleteMapping("/products")
	public ResponseEntity<HttpStatus> deleteAllProducts() {
		try {
			productRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
