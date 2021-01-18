package com.clinic.billing.consumer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.clinic.billing.util.Constants;

@Configuration
public class ConsumerConfig {
	

	@Bean
	Queue queue() {
		Map <String,Object> map =new HashMap<String,Object>();
		map.put("x-message-ttl", 100000);
		return new Queue(Constants.VISIT_BILLING_QUEUE,false,false,false,map);
	}
		
	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(Constants.VISIT_BILLING_QUEUE);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(VisitReceiver receiver) {
		return new MessageListenerAdapter(receiver, "messageReceived");
	}

}
