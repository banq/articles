package com.example.springbatchparallel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.support.ReferenceJobFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.batch.core.Job;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value="/job/")
public class JobLauncherController {

	private static final Log LOG = LogFactory.getLog(JobLauncherController.class);

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;


	@RequestMapping("/launchjob/{jobName}")
	public String handle(@PathVariable("jobName") String jobName) throws Exception {
		try {
			JobParameters jobParameters = new JobParametersBuilder()
					.addString("jobName", jobName).addLong
					("time", System.currentTimeMillis())
					.toJobParameters();
			jobLauncher.run(job, jobParameters);
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
		}

		return "Done";
	}



}