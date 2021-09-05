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

import com.example.recipes.model.Uom;
import com.example.recipes.repository.UomRepository;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class UomController {
	
	@Autowired
	UomRepository uomRepository;
	
	@GetMapping("/uom")
	public ResponseEntity<List<Uom>> getAllUom () {
		try {
			List<Uom> uoms = new ArrayList<>();
			
			uomRepository.findAll().forEach(uoms::add);
			
			if (uoms.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(uoms, HttpStatus.OK);	
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("/uom")
	public ResponseEntity<Uom> addUom (@RequestBody Uom uom) {
		try {
			Uom _uom = uomRepository.findByName(uom.getUomName());
			
			if (_uom == null) {
				_uom = new Uom(uom.getUomName());
				uomRepository.saveAndFlush(_uom);
			} else {
				return new ResponseEntity<>(null, HttpStatus.CONFLICT);
			}
			
			return new ResponseEntity<>(_uom, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@DeleteMapping("/uom")
	public ResponseEntity<HttpStatus> deleteAllUom () {
		try {
			uomRepository.deleteAll();
		
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
