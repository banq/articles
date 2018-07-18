package com.example.mysqlspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.RandomAccessFile;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationDao applicationDao;

    public ResponseEntity<byte[]> getImageURL() throws IOException {
        RequestData requestData = applicationDao.findResource(1);
        String imagePath = requestData.getImages();
        RandomAccessFile f = new RandomAccessFile(imagePath, "r");
        byte[] b = new byte[(int) f.length()];
        f.readFully(b);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<byte[]>(b, headers, HttpStatus.CREATED);
    }

    public ResponseEntity<byte[]> getTextData() throws IOException {
        RequestData requestData = applicationDao.findResource(1);
        String s = requestData.getContents();
        RandomAccessFile f = new RandomAccessFile(s, "r");
        byte[] b = new byte[(int) f.length()];
        f.readFully(b);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<byte[]>(b, headers, HttpStatus.CREATED);
    }
}