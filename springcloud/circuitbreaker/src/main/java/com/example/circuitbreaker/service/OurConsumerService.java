package com.example.circuitbreaker.service;

import com.example.circuitbreaker.domain.Article;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OurConsumerService {
    @Autowired
    ConsumerService consumerService;

    @HystrixCommand(fallbackMethod = "countArticleDefault")
    public Float countArticle(){
        List<Article> articles = consumerService.getAllArticles();
        float fee = 0.0f;
        for (Article article : articles) {
           fee = fee + article.getPrice();
        }
        return fee;
    }

    public Float countArticleDefault(){
        return 99.99f;
    }
}
