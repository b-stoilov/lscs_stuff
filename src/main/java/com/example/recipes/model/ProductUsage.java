package com.example.recipes.model;

import javax.persistence.*;

@Entity
@Table(name="productusage")
public class ProductUsage {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	private Product product;
	
	@Column(name="product_quantity")
	private long productQuantity;
	
	@Column(name="product_uom")
	private String productUOM;
	
	@Column(name="recipe_id")
	private long recipeId;
	
	public ProductUsage () {
		
	}
	
	public ProductUsage(Product product, long productQuantity, String productUOM, long recipeId) {
		this.product = product;
		this.productQuantity = productQuantity;
		this.productUOM = productUOM;
		this.recipeId = recipeId;
	}

	public long getId() {
		return id;
	}
	
	public void setProduct (Product product) {
		this.product = product;
	}
	
	public Product getProduct () {
		return product;
	}

	public long getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(long productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getProductUOM() {
		return productUOM;
	}

	public void setProductUOM(String productUOM) {
		this.productUOM = productUOM;
	}
	
	public long getRecipeId() {
		return recipeId;
	}
	
	
}
