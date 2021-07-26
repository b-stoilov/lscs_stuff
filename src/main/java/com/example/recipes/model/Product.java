package com.example.recipes.model;

import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import net.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy;

@Entity
@Table(name = "products")
public class Product {
	
	@Id
	@GeneratedValue(generator = "prod_seq")
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy="product", cascade = CascadeType.ALL)
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
