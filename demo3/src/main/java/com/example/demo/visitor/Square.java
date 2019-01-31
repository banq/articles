package com.example.demo.visitor;


public class Square implements Element {
	public final double side;

	public Square(double side) {
		this.side = side;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}


}

