package com.example.cloudbusstreamkafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GreetingsListener {
    @StreamListener(GreetingsStreams.INPUT)
	@SendTo(Processor.OUTPUT)
    public String handleGreetings(Greetings greetings) {
        log.info("Received greetings: {}", greetings);
        return "Received greetings: {}" + greetings;
    }
}