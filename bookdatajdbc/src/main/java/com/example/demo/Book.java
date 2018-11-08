package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.Set;

public class Book {
	@Id
	Long id;
	String title;
	private Set<AuthorRef> authors = new HashSet<>();

	public void addAuthor(Author author) {
		authors.add(createAuthorRef(author));
	}

	private AuthorRef createAuthorRef(Author author) {

		Assert.notNull(author, "Author must not be null");
		Assert.notNull(author.id, "Author id, must not be null");

		AuthorRef authorRef = new AuthorRef();
		authorRef.authorId = author.id;
		return authorRef;
	}
}
