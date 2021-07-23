package com.example.recipes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.recipes.model.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Long>{
	List<Equipment> findByNameContaining(String name);
}
