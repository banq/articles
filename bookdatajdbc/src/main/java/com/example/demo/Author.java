package com.example.demo;

import org.springframework.data.annotation.Id;

public class Author {
	String name;
	@Id
	Long id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
