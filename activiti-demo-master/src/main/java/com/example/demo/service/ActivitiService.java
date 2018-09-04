package com.example.demo.service;

import java.util.List;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActivitiService {
	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	@Transactional
	public void startProcess() {
		runtimeService.startProcessInstanceByKey("simple");
	}
	
	@Transactional
	public void startProcessByKey(String process) {
		runtimeService.startProcessInstanceByKey(process);
	}

	@Transactional
	public List<Task> getTasks(String assignee) {
		return taskService.createTaskQuery().taskAssignee(assignee).list();
	}
	
	@Transactional
	public List<Task> getAllTasks() {
		return taskService.createTaskQuery().list();
	}
}
