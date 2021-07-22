package com.example.recipes.model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy="product")
	private Set<ProductUsage> prodUsages;
	
	public Product () {}
	
	public Product (String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + "]";
	}

	
	
}
