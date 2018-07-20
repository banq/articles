package com.example.consumer.discoveryclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Controller
public class ConsumerDiscoveryClient {

    @Autowired
    private DiscoveryClient discoveryClient;


    public String getProducerMethod() throws RestClientException, IOException {

        List<ServiceInstance> instances = discoveryClient.getInstances("PengProducerService");
        ServiceInstance serviceInstance = instances.get(1);

        String baseUrl = serviceInstance.getUri().toString();

        baseUrl = baseUrl + "/pengproducer";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), String.class);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        String result = response.getBody() + " called in getProducerMethod";
        System.out.println(result);
        return result;
    }

    private static HttpEntity<?> getHeaders() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(headers);
    }
}
