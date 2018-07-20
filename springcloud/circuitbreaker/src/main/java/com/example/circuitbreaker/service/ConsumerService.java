package com.example.circuitbreaker.service;

import com.example.circuitbreaker.domain.Article;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="PengProducerService")
public interface ConsumerService {

    @GetMapping("/articles")
    List<Article> getAllArticles();
}
