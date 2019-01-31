package com.example.demo;

public class ResourceUser extends AbstractResourceManipulatorTemplate {
	@Override
	protected void doSomethingWithResource() {
		resource.useResource();
	}
}