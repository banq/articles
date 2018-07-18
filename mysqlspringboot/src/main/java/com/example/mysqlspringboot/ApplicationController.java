package com.example.mysqlspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/application")
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    @RequestMapping(value = "/get-image", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImagePath() throws IOException {
        ResponseEntity<byte[]> responseEntity = applicationService.getImageURL();
        return responseEntity;
    }
    @RequestMapping(value = "/get-text", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getTextPath() throws IOException {
        ResponseEntity<byte[]> responseEntity = applicationService.getTextData();
        return responseEntity;
    }
}