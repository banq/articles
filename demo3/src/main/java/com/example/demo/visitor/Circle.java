package com.example.demo.visitor;

public class Circle implements Element {
	public final double radius;

	public Circle(double radius) {
		this.radius = radius;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
