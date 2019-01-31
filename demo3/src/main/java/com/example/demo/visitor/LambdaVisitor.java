package com.example.demo.visitor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class LambdaVisitor<A> implements Function<Object, A> {
	private Map<Class<?>, Function<Object, A>> fMap = new HashMap<>();

	public <B> Acceptor<A, B> on(Class<B> clazz) {
		return new Acceptor<>(this, clazz);
	}

	@Override
	public A apply(Object o) {
		return fMap.get(o.getClass()).apply(o);
	}

	static class Acceptor<A, B> {
		private final LambdaVisitor visitor;
		private final Class<B> clazz;

		Acceptor(LambdaVisitor<A> visitor, Class<B> clazz) {
			this.visitor = visitor;
			this.clazz = clazz;
		}

		public LambdaVisitor<A> then(Function<B, A> f) {
			visitor.fMap.put(clazz, f);
			return visitor;
		}
	}
}
