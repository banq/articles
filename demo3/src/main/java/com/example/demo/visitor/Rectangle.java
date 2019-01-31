package com.example.demo.visitor;

public class Rectangle implements Element {
	public final double width;
	public final double height;

	public Rectangle(double width, double height) {
		this.width = width;
		this.height = height;
	}

	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
