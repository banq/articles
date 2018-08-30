package com.example.cloudbusstreamkafka;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.messaging.support.MessageBuilder;

@Service
@Slf4j
public class GreetingsService {
    private final GreetingsStreams greetingsStreams;

    public GreetingsService(GreetingsStreams greetingsStreams) {
        this.greetingsStreams = greetingsStreams;
	}


    public void sendGreeting(final Greetings greetings) {
        log.info("Sending greetings {}", greetings);
		greetingsStreams.outboundGreetings().send(MessageBuilder
                .withPayload(greetings)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }
}