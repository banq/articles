package com.example.producer;

import com.example.producer.domain.Article;
import com.example.producer.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProducerService {

    @Autowired
    ArticleService articleService;

    @GetMapping("/articles")
    public List<Article> getAllArticles(){
        System.out.printf(" getAllArticles is called in " + this.hashCode());
        return articleService.getAllArticles();
    }

    @PostMapping("/article")
    public void publishArticle(@RequestBody Article article){
        articleService.insertArticle(article);
    }

    @GetMapping("/pengproducer")
    public String sayHello() {
        //如果ProducerService启动多个实例，hashCode应该不同
        //客户端能够根据这个值判断调用了不同的ProducerService实例
        System.out.printf(" sayHello is called in " + this.hashCode());
        return "hello world " + this.hashCode();
    }

}
