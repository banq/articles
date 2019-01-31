package com.example.demo.visitor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;

//https://www.jdon.com/51636 访问者模式
public class VisitorCLient {
	static Function<Object, Double> areaCalculator = new LambdaVisitor<Double>()
			.on(Square.class).then(s -> s.side * s.side)
			.on(Circle.class).then(c -> Math.PI * c.radius * c.radius)
			.on(Rectangle.class).then(r -> r.height * r.width);
	private final Map<Object, Consumer<Object>> listeners = new ConcurrentHashMap<>();

	public static void main(String[] args) {

	}
}
