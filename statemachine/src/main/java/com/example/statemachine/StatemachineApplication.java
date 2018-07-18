package com.example.statemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachine;

@SpringBootApplication
@EnableStateMachine
public class StatemachineApplication implements CommandLineRunner {
    private final StateMachine<BookStates, BookEvents> stateMachine;

    public static void main(String[] args) {
        SpringApplication.run(StatemachineApplication.class, args);
    }

    @Autowired
    public StatemachineApplication(StateMachine<BookStates, BookEvents> stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public void run(String... args) {
        stateMachine.start();
        stateMachine.sendEvent(BookEvents.RETURN);
        stateMachine.sendEvent(BookEvents.BORROW);
        stateMachine.stop();
    }
}
