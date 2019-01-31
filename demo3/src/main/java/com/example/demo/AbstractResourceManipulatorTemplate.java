package com.example.demo;

public abstract class AbstractResourceManipulatorTemplate {
	protected Resource resource;

	private void openResource() {
		resource = new Resource();
	}

	protected abstract void doSomethingWithResource();

	private void closeResource() {
		resource.dispose();
		resource = null;
	}

	public void execute() {
		openResource();
		try {
			doSomethingWithResource();
		} finally {
			closeResource();
		}
	}
}
