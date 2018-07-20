package com.example.producer.repository;

import com.example.producer.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRep extends JpaRepository<Article,Long> {
}
