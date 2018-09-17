package com.doublexman.xxbpm.test.process;

import com.doublexman.xxbpm.Application;
import org.activiti.engine.RepositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProcessTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RepositoryService repositoryService;

    @Test
    public void testProcessDeployment(){
        repositoryService.createDeployment().addClasspathResource("process/VacationRequest.bpmn20.xml").deploy();
        logger.info("Number of process definitions: " + repositoryService.createProcessDefinitionQuery().count());
    }
}
