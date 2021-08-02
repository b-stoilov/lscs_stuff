package com.example.recipes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;

import com.example.recipes.model.Equipment;
import com.example.recipes.model.EquipmentUsage;

public interface EquipmentUsageRepository extends JpaRepository<EquipmentUsage, Long> {
	List<EquipmentUsage> getById (long id);
	EquipmentUsage findById (long id);

}
