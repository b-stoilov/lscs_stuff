package com.example.recipes.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="productusage")
public class ProductUsage {

	@Id
	@GeneratedValue(generator = "prod_usg_seq")
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	private Product product;
	
	@Column(name="product_quantity")
	private double productQuantity;
	
	@ManyToOne
	@JoinColumn(name = "uom_id", referencedColumnName= "uom_id")
	private Uom productUOM;
	
	@ManyToOne
	@JoinColumn(name="recipe_id", referencedColumnName = "id")
	private Recipe recipe;
	
	@Transient
	private long recipeIdd;
	
	public ProductUsage () {}
	
	public ProductUsage(Product product, double productQuantity, Uom productUOM, Recipe recipe) {
		this.product = product;
		this.productQuantity = productQuantity;
		this.productUOM = productUOM;
		this.recipe = recipe;
	}
	
	public ProductUsage(Product product, double productQuantity, Uom productUOM, long recipeIdd) {
		this.product = product;
		this.productQuantity = productQuantity;
		this.productUOM = productUOM;
		this.recipeIdd = recipeIdd;
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

	public double getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(double productQuantity) {
		this.productQuantity = productQuantity;
	}

	public Uom getProductUOM() {
		return productUOM;
	}

	public void setProductUOM(Uom productUOM) {
		this.productUOM = productUOM;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	
	public long getRecipeIdd() {
		return recipeIdd;
	}

	public void setRecipeIdd(long recipeIdd) {
		this.recipeIdd = recipeIdd;
	}
	
	
	
	
}
