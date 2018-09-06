package org.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.cfg.StandaloneInMemProcessEngineConfiguration;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) {

		ProcessEngine processEngine = new StandaloneInMemProcessEngineConfiguration()
				.buildProcessEngine();
		RepositoryService repositoryService = processEngine.getRepositoryService();
		RuntimeService runtimeService = processEngine.getRuntimeService();

		TaskService taskService = processEngine.getTaskService();

		repositoryService.createDeployment().addClasspathResource("process.bpmn20.xml").deploy();

		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("keyWord", "workflow");
		variables.put("language", "java");
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("githubsearch",
				variables);

		List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstance.getId()
		).list();
		for (Task task : tasks) {
			System.out.println("Current task : " + task.getName());
			Map<String, Object> taskVariables = new HashMap();
			taskVariables.put("reviewResult", "ok");
			ExpressSample expressSample = new ExpressSample();
			//taskVariables.put("expressSample", expressSample);
			taskService.complete(task.getId(), taskVariables);
		}


	}

}
