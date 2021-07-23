package com.example.recipes.model;

import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name = "equipment")
public class Equipment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_new_generator")
    @SequenceGenerator(name = "author_new_generator", sequenceName = "author_new_seq")
	private long id;
	
	@Column(name="e_name")
	private String name;
	
	@OneToMany(mappedBy="equipment", cascade = CascadeType.ALL)
	private Set<EquipmentUsage> eqUsages;
	
	public Equipment() {
		
	}

	public Equipment(String equipmntName) {
		this.name = equipmntName;
	}

	public String getEquipmntName() {
		return name;
	}

	public void setEquipmntName(String equipmntName) {
		this.name = equipmntName;
	}
	
	public long getId () {
		return id;
	}

	
}
