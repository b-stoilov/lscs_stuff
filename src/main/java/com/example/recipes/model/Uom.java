package com.example.recipes.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "uom")
public class Uom {
	
	@Id
	@GeneratedValue(generator = "uom_gen")
	@Column(name = "uom_id")
	private long id;
	
	@Column(name = "uom_name")
	private String name;
	
	@OneToMany(mappedBy="product", cascade = CascadeType.ALL)
	private Set<ProductUsage> prodUsages;
	
	public Uom () {}
	
	public Uom(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getUomName() {
		return name;
	}

	public void setUomName(String uomName) {
		this.name = uomName;
	}
	
	
	
	
	
	
	
}
