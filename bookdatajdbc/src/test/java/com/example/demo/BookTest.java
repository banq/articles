package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJdbcTest
public class BookTest {
	@Autowired
	BookRepo bookRepo;
	@Autowired
	AuthorRepo authorRepo;

	@Test
	public void booksAndAuthors() {

		Author author = new Author();
		author.name = "Greg L. Turnquist";

		author = authorRepo.save(author);

		Book book = new Book();
		book.title = "Spring Boot";
		book.addAuthor(author);

		bookRepo.save(book);

		bookRepo.deleteAll();

		assertThat(authorRepo.count()).isEqualTo(1);
	}


}
