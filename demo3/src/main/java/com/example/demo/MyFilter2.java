package com.example.demo;

import java.util.function.Function;

public class MyFilter2 implements Function<String, String> {


	@Override
	public String apply(String o) {
		return o + "+MyFilter2+";
	}
}