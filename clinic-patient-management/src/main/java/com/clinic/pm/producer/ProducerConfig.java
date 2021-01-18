package com.clinic.pm.producer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.clinic.pm.util.Constants;

@Configuration
public class ProducerConfig {
		
	@Bean
	Queue queue() {
		Map <String,Object> map =new HashMap<String,Object>();
		map.put("x-message-ttl", 100000);
		return new Queue(Constants.VISIT_BILLING_QUEUE,false,false,false,map);
	}
	
	@Bean
	TopicExchange exchange() {
		return new TopicExchange(Constants.VISIT_BILLING_TOPIC_EXCHANGE);
	}
	
	@Bean
	Binding binding (Queue queue,TopicExchange exchange ) {
		return BindingBuilder.bind(queue).to(exchange).with(Constants.VISIT_BILLING_ROUTING_KEY);
	}
	

}
