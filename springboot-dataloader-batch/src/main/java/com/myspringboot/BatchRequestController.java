package com.myspringboot;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myspringboot.provider.ServiceProvider;

@RestController
@RequestMapping(value = "/batch")
public class BatchRequestController {

	private static final Logger log = LoggerFactory.getLogger(BatchRequestController.class);
	private static final String BATCHSTATUSCOMPLETED= "COMPLETED";

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job job;

	@Autowired
	ServiceProvider serviceProvider;

	@RequestMapping("/test")
	public String helloGradle() {
		return "Tested Ok!";
	}

	// @RequestMapping(value= "/loader")
	//@Scheduled(cron ="${dataloaderbatch.cronexpression:0 40 16 * * ?}")
	@Scheduled(cron ="${dataloaderbatch.cronexpression}")
	public void dataLoader() throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		Map<String, JobParameter> jobparameters = new HashMap<>();
		jobparameters.put("timestamp", new JobParameter(System.currentTimeMillis()));
		JobParameters jobParameters = new JobParameters(jobparameters);

		JobExecution jobExecution = jobLauncher.run(job, jobParameters);
		switch(jobExecution.getStatus().toString())
		{
		case BATCHSTATUSCOMPLETED:
			log.info("BATCH " +BATCHSTATUSCOMPLETED +" SUCCESSFULLY!");
			break;
		default:
		log.info("OHH NO..BATCH " + jobExecution.getStatus() + "!");
		break;
		}
     }
	
	
}
