package com.example.demo.controller;

import java.util.List;

import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ActivitiService;

@RestController
public class ActivitiController {

	@Autowired
	private ActivitiService activitiService;

	@RequestMapping(value="/start", method= RequestMethod.GET, produces=MediaType
			.APPLICATION_JSON_VALUE)
	public void startProcessInstance() {
		activitiService.startProcess();
	}
	
	@RequestMapping(value="/tasks", method= RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public String getTaskSize(@RequestParam String assignee) {
		List<Task> tasks = activitiService.getTasks(assignee);
		return String.valueOf(tasks.size());
	}
}
