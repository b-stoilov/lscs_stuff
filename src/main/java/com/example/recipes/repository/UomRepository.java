package com.example.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.recipes.model.Uom;

public interface UomRepository extends JpaRepository<Uom, Long> {
	Uom findByName (String name);
}
