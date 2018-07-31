package com.example.taskbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.cloud.task.listener.annotation.AfterTask;
import org.springframework.cloud.task.listener.annotation.BeforeTask;
import org.springframework.cloud.task.listener.annotation.FailedTask;
import org.springframework.cloud.task.repository.TaskExecution;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
@EnableTask
@EnableBatchProcessing
public class TaskbatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskbatchApplication.class, args);
	}


	@Bean
	public GreetingTask greetingTask(){
		return new GreetingTask();
	}

	public static class GreetingTask implements ApplicationRunner {



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

		@Override
		public void run(ApplicationArguments args) throws Exception {
			System.out.println("#########Hello World from Spring Cloud Task!");
		}
	}
}
