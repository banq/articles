package com.example.product.demoproduct.domain;


import javax.persistence.Entity;

/**
 * @author banq
 * @version 1.0
 * @created 09-10��-2018 11:24:12
 */
@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {
	@javax.persistence.Id
	private String Id;
	private String name;


	public Product() {

	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void finalize() throws Throwable {

	}

}