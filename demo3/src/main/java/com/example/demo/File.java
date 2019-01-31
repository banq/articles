package com.example.demo;

public class File {
	private final Type type;
	private final String content;

	public File(Type type, String content) {
		this.type = type;
		this.content = content;
	}

	public Type getType() {
		return type;
	}

	public String getContent() {
		return content;
	}

	@Override
	public String toString() {
		return type + ": " + content;
	}

	enum Type {TEXT, AUDIO, VIDEO}
}
