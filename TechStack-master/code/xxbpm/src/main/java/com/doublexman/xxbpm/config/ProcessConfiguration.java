package com.doublexman.xxbpm.config;

import org.activiti.engine.*;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class ProcessConfiguration {

    @Autowired
    DataSource dataSource;

    @Autowired
    PlatformTransactionManager transactionManager;

    /**
     * Define process engine configuration
     */
    @Bean
    public SpringProcessEngineConfiguration processEngineConfiguration() {
        SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();
        configuration.setDataSource(dataSource);
        configuration.setTransactionManager(transactionManager);
        configuration.setHistory(HistoryLevel.FULL.getKey());
        return configuration;
    }

    /**
     * Define process engine factory bean
     */
    @Bean
    public ProcessEngineFactoryBean processEngineFactoryBean(){
        ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
        processEngineFactoryBean.setProcessEngineConfiguration(processEngineConfiguration());
        return processEngineFactoryBean;
    }

    /**
     * Process engine
     */
    @Bean
    public ProcessEngine processEngine() throws Exception {
        return processEngineFactoryBean().getObject();
    }

    /**
     * Runtime service
     */
    @Bean
    public RuntimeService runtimeService() throws Exception {
        return processEngine().getRuntimeService();
    }

    /**
     * Repository service
     */
    @Bean
    public RepositoryService repositoryService() throws Exception {
        return processEngine().getRepositoryService();
    }

    /**
     * History service
     */
    @Bean
    public HistoryService historyService() throws Exception {
        return processEngine().getHistoryService();
    }

    /**
     * Task service
     */
    @Bean
    public TaskService taskService() throws Exception {
        return processEngine().getTaskService();
    }
}
