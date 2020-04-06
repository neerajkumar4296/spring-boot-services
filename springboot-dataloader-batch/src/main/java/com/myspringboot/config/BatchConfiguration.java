package com.myspringboot.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.myspringboot.model.Person;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Value("${input.filename}")
	private String fileName;
	
	@Value("${dataloaderbatch.stepname}")
	private String stepName;
	
	@Value("${dataloaderbatch.jobname}")
	private String jobName;
	
	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
			ItemReader<Person> itemReader, ItemProcessor<Person, Person> itemProcessor, ItemWriter<Person> itemWriter) {

		Step step = stepBuilderFactory.
				get(stepName)
				.<Person, Person>chunk(10)
				.reader(itemReader)
				.processor(itemProcessor)
				.writer(itemWriter)
				.build();

		return jobBuilderFactory
				.get(jobName)
				.incrementer(new RunIdIncrementer())
				.start(step).build();
	}

	@Bean
	public FlatFileItemReader<Person> itemReader() {
		return new FlatFileItemReaderBuilder<Person>()
				.name("personItemReader")
				.resource(new ClassPathResource(fileName))
				.delimited()
				.names(new String[] { "firstName", "lastName"})
				.fieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {
					{
						setTargetType(Person.class);
					}
				}).build();
	}

}
