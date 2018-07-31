package com.example.task;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.cloud.task.listener.TaskExecutionListener;
import org.springframework.cloud.task.listener.annotation.AfterTask;
import org.springframework.cloud.task.listener.annotation.BeforeTask;
import org.springframework.cloud.task.listener.annotation.FailedTask;
import org.springframework.cloud.task.repository.TaskExecution;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableTask
public class TaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskApplication.class, args);
	}

	@Bean
	public GreetingTask greetingTask(){
		return new GreetingTask();
	}

	public static class GreetingTask implements CommandLineRunner {

		@Override
		public void run(String... args) throws Exception {
			System.out.println("############message");
		}

		@AfterTask
		public void onTaskEnd(TaskExecution arg0) {
			System.out.println("Task Completed Sucessfully");
		}

		@FailedTask
		public void onTaskFailed(TaskExecution arg0, Throwable arg1) {
			System.out.println("Task Failed");
		}

		@BeforeTask
		public void onTaskStartup(TaskExecution arg0) {
			System.out.println("Task started");
		}
	}
}
