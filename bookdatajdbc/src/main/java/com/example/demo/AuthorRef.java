package com.example.demo;

import org.springframework.data.relational.core.mapping.Table;

@Table("Book_Author")
public class AuthorRef {

	Long authorId;
}
