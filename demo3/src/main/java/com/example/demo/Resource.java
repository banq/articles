package com.example.demo;

import java.util.Random;

public class Resource {
	public Resource() {
		System.out.println("Resource created");
	}

	public void useResource() {
		riskyOperation();
		System.out.println("Resource used");
	}

	public void employResource() {
		riskyOperation();
		System.out.println("Resource employed");
	}

	public void dispose() {
		System.out.println("Resource disposed");
	}

	private void riskyOperation() {
		if (new Random().nextInt(10) == 0) {
			throw new RuntimeException();
		}
	}

}
