package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MyFilter implements Function<String, String> {
	public static void main(String[] args) {
		MyFilter myFilter = new MyFilter();
		MyFilter2 myFilter2 = new MyFilter2();
		List fs = new ArrayList();
		fs.add(myFilter);
		fs.add(myFilter2);
		String r = myFilter.andThen(myFilter2).apply("qq");
		System.out.println("=" + r);
		r = myFilter.compose(myFilter2).apply("qq");
		System.out.println("----" + r);
	}

	@Override
	public String apply(String o) {
		return o + "+MyFilter+";
	}
}
