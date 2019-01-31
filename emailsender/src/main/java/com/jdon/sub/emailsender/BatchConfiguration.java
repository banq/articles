package com.jdon.sub.emailsender;

import com.jdon.sub.emailsender.mailservice.JobCompletionNotificationListener;
import com.jdon.sub.emailsender.mailservice.MailBatchItemWriter;
import com.jdon.sub.emailsender.mailservice.MailItemProcessor;
import com.jdon.sub.emailsender.model.Subscriber;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	private static final String QUERY_FIND_STUDENTS =
			"SELECT email FROM email_sub ";
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DataSource dataSource;


	@Bean
	public ItemReader<Subscriber> reader() {
		JdbcCursorItemReader<Subscriber> databaseReader = new JdbcCursorItemReader<>();

		databaseReader.setDataSource(dataSource);
		databaseReader.setSql(QUERY_FIND_STUDENTS);
		databaseReader.setFetchSize(10);
		databaseReader.setRowMapper(new BeanPropertyRowMapper<>(Subscriber.class));

		return databaseReader;
	}

	@Bean
	public MailItemProcessor processor() {
		return new MailItemProcessor();
	}

	@Bean
	public MailBatchItemWriter writer() {
		MailBatchItemWriter writer = new MailBatchItemWriter();
		return writer;
	}
	// end::readerwriterprocessor[]

	// tag::listener[]

	@Bean
	public JobExecutionListener listener() {
		return new JobCompletionNotificationListener();
	}

	// end::listener[]

	// tag::jobstep[]
	@Bean
	public Job importUserJob() {
		return jobBuilderFactory.get("importUserJob")
				.incrementer(new RunIdIncrementer())
				.listener(listener())
				.flow(step1())
				.end()
				.build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.<Subscriber, MimeMessagePreparator>chunk(10)
				.reader(reader())
				.processor(processor())
				.writer(writer())
				.build();
	}
	// end::jobstep[]
}
