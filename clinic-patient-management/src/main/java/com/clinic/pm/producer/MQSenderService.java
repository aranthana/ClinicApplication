package com.clinic.pm.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clinic.models.common_models.Visit;
import com.clinic.pm.util.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MQSenderService{
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void send(Visit visit)  {
		log.info("Sending message to Topic : " +Constants.VISIT_BILLING_TOPIC_EXCHANGE + " : visit id = "+visit.getId());
		rabbitTemplate.convertAndSend(Constants.VISIT_BILLING_TOPIC_EXCHANGE, Constants.VISIT_BILLING_ROUTING_KEY, visit );
	}
}
