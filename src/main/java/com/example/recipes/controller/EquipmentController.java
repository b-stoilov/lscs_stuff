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

import com.example.recipes.model.Equipment;
import com.example.recipes.repository.EquipmentRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class EquipmentController {
	
	@Autowired
	EquipmentRepository equipmentRepository;
	
	@GetMapping("/equipment")
	public ResponseEntity<List<Equipment>> getAllEquipment () {
		try {
			List<Equipment> equipment = new ArrayList<>();
		
			equipmentRepository.findAll().forEach(equipment::add);
		
			if (equipment.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(equipment, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/equipment")
	public ResponseEntity<Equipment> addEquipment (@RequestBody Equipment equipment) {
		try {
			if (equipmentRepository.findByName(equipment.getEquipmntName()) == null) {
				Equipment _equipment = equipmentRepository
					.save(new Equipment(equipment.getEquipmntName()));
			
				return new ResponseEntity<>(_equipment, HttpStatus.OK);
			}
			
			return new ResponseEntity<>(null, HttpStatus.CONFLICT);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/equipment")
	public ResponseEntity<HttpStatus> deleteAllEquipment () {
		try {
			equipmentRepository.deleteAll();
			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
