package com.example.springbatchremotechunkslave;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.step.item.ChunkProcessor;
import org.springframework.batch.core.step.item.SimpleChunkProcessor;
import org.springframework.batch.integration.chunk.ChunkProcessorChunkHandler;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.jms.dsl.Jms;

@Configuration
@EnableBatchProcessing
@EnableIntegration
public class WorkerConfiguration {

	@Value("${broker.url}")
	private String brokerUrl;

	@Bean
	public ActiveMQConnectionFactory jmsConnectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(this.brokerUrl);
		connectionFactory.setTrustAllPackages(true);
		return connectionFactory;
	}

	/*
	 * Configure inbound flow (requests coming from the master)
	 */

	@Bean
	public DirectChannel requests() {
		return new DirectChannel();
	}

	@Bean
	public IntegrationFlow incomingRequests(ActiveMQConnectionFactory jmsConnectionFactory) {
		return IntegrationFlows
				.from(Jms.messageDrivenChannelAdapter(jmsConnectionFactory).destination("requests"))
				.channel(requests())
				.get();
	}

	/*
	 * Configure outbound flow (replies going to the master)
	 */

	@Bean
	public DirectChannel replies() {
		return new DirectChannel();
	}

	@Bean
	public IntegrationFlow outgoingReplies(ActiveMQConnectionFactory jmsConnectionFactory) {
		return IntegrationFlows
				.from(replies())
				.handle(Jms.outboundAdapter(jmsConnectionFactory).destination("replies"))
				.get();
	}

	/*
	 * Configure worker components
	 */

	@Bean
	public ItemProcessor<Integer, Integer> itemProcessor() {
		return item -> {
			System.out.println("processing item " + item);
			return item;
		};
	}

	@Bean
	public ItemWriter<Integer> itemWriter() {
		return items -> {
			for (Integer item : items) {
				System.out.println("writing item " + item + System.currentTimeMillis());
			}
		};
	}

	@Bean
	@ServiceActivator(inputChannel = "requests", outputChannel = "replies")
	public ChunkProcessorChunkHandler<Integer> chunkProcessorChunkHandler() {
		ChunkProcessor<Integer> chunkProcessor = new SimpleChunkProcessor<>(itemProcessor(), itemWriter());
		ChunkProcessorChunkHandler<Integer> chunkProcessorChunkHandler = new ChunkProcessorChunkHandler<>();
		chunkProcessorChunkHandler.setChunkProcessor(chunkProcessor);
		return chunkProcessorChunkHandler;
	}

}
