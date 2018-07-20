package com.example.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ConsumerApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(ConsumerApplication
                .class, args);
        ConsumerService consumerService = ctx.getBean(ConsumerService.class);
        System.out.printf("final result RestTemplate=" + consumerService
                .callProducer() + " \n");
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        //application.properties中激活ribbon的https:ribbon.IsSecure=true
//        CloseableHttpClient httpClient = HttpClientUtil.getHttpClient();
//        HttpComponentsClientHttpRequestFactory clientrequestFactory = new HttpComponentsClientHttpRequestFactory();
//        clientrequestFactory.setHttpClient(httpClient);
//        RestTemplate restTemplate = new RestTemplate(clientrequestFactory);
//        return restTemplate;
        return new RestTemplate();
    }
}
