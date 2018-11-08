package io.github.benas;

import java.util.Arrays;

import org.apache.activemq.ActiveMQConnectionFactory;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.integration.chunk.ChunkMessageChannelItemWriter;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.jms.dsl.Jms;

@Configuration
@EnableBatchProcessing
@EnableIntegration
@PropertySource("classpath:application.properties")
public class MasterConfiguration {

	@Value("${broker.url}")
	private String brokerUrl;

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public ActiveMQConnectionFactory jmsConnectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(this.brokerUrl);
		connectionFactory.setTrustAllPackages(true);
		return connectionFactory;
	}

	/*
	 * Configure outbound flow (requests going to workers)
	 */

	@Bean
	public DirectChannel requests() {
		return new DirectChannel();
	}

	@Bean
	public IntegrationFlow outboundFlow(ActiveMQConnectionFactory jmsConnectionFactory) {
		return IntegrationFlows
				.from(requests())
				.handle(Jms.outboundAdapter(jmsConnectionFactory).destination("requests"))
				.get();
	}

	/*
	 * Configure inbound flow (replies coming from workers)
	 */

	@Bean
	public QueueChannel replies() {
		return new QueueChannel();
	}

	@Bean
	public IntegrationFlow inboundFlow(ActiveMQConnectionFactory jmsConnectionFactory) {
		return IntegrationFlows
				.from(Jms.messageDrivenChannelAdapter(jmsConnectionFactory).destination("replies"))
				.channel(replies())
				.get();
	}

	/*
	 * Configure master step components
	 */

	@Bean
	public ItemReader<Integer> itemReader() {
		return new ListItemReader<>(Arrays.asList(1, 2, 3, 4, 5, 6));
	}

	@Bean
	public ItemWriter<Integer> itemWriter() {
		MessagingTemplate messagingTemplate = new MessagingTemplate();
		messagingTemplate.setDefaultChannel(requests());
		ChunkMessageChannelItemWriter<Integer> chunkMessageChannelItemWriter = new ChunkMessageChannelItemWriter<>();
		chunkMessageChannelItemWriter.setMessagingOperations(messagingTemplate);
		chunkMessageChannelItemWriter.setReplyChannel(replies());
		return chunkMessageChannelItemWriter;
	}

	@Bean
	public TaskletStep masterStep() {
		return this.stepBuilderFactory.get("masterStep")
				.<Integer, Integer>chunk(3)
				.reader(itemReader())
				.writer(itemWriter())
				.build();
	}

	@Bean
	public Job remoteChunkingJob() {
		return this.jobBuilderFactory.get("remoteChunkingJob")
				.start(masterStep())
				.build();
	}
}
