package com.example.producer.service;

import com.example.producer.domain.Article;
import com.example.producer.repository.ArticleRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticleRep articleRep;

    public List<Article> getAllArticles(){
        return articleRep.findAll();
    }

    public void insertArticle(Article article){
        articleRep.save(article);
    }
}
