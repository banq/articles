package com.example.demo.visitor;


public interface Element {
	<T> T accept(Visitor<T> visitor);
}
