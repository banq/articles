package com.example.demo;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.activiti.spring.boot.SecurityAutoConfiguration;

//@SpringBootApplication(exclude = SpringDataWebAutoConfiguration.class)
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ActivitiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivitiApplication.class, args);
	}

	public void printEngine() {
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		System.out.println("EngineName: " + processEngine.getName());
		
		// init will be called by default
		//ProcessEngines.init();
		Map<String, ProcessEngine> engines = ProcessEngines.getProcessEngines();
		System.out.println("EngineSize: " + engines.size());
	}
	
	public void CreateGroupCmd() {
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		IdentityService identityService =  processEngine.getIdentityService();
		String genId = UUID.randomUUID().toString();
		org.activiti.engine.identity.Group group = identityService.newGroup(genId);
		group.setName("Employee");
		group.setType("Employee");
		group.setId(null);
		identityService.saveGroup(group);
	}
	
	@Bean
	public CommandLineRunner init(final RepositoryService repositoryService, final RuntimeService runtimeService,
			final TaskService taskService) {

		return new CommandLineRunner() {
			@Override
			public void run(String... strings) throws Exception {
				
				printEngine();
				//CreateGroupCmd();
				
				System.out.println(
						"Number of process definitions : " + repositoryService.createProcessDefinitionQuery().count());
				System.out.println("Number of tasks : " + taskService.createTaskQuery().count());
				//runtimeService.startProcessInstanceByKey("simple");
				System.out.println("Number of tasks after process start: " + taskService.createTaskQuery().count());
				List<Task> tasks = taskService.createTaskQuery().list();
				for (Task task : tasks) {
					System.out.println("TaskName: " + task.getName());
					System.out.println("TaskAssignee: " + task.getAssignee());
					task.setAssignee("user1");
					// 修改以后要save
					taskService.saveTask(task);
					//taskService.setAssignee(task.getId(), user.getId());
					//System.out.println("TaskAssignee: " + task.getAssignee());
				}
			}
		};

	}
}
