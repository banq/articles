package com.example.feignconsumer.service;

import com.example.feignconsumer.domain.Article;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="PengProducerService")
public interface ConsumerService {

    @GetMapping("/articles")
    List<Article> getAllArticles();
}
