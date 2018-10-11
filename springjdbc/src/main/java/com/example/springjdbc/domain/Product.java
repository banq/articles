package com.example.springjdbc.domain;

import org.springframework.data.annotation.Id;

/**
 * @author banq
 * @version 1.0
 * @created 09-10��-2018 11:24:12
 */

public class Product {
	@Id
	private Integer id;
	private String name;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}