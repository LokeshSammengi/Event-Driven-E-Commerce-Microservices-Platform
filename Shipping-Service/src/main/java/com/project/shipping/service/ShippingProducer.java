package com.project.shipping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.project.events.OrderShippedEvent;

@Service
public class ShippingProducer {

	@Autowired
	private KafkaTemplate< String,OrderShippedEvent> kafkaTemplate;
	
	public static final String TOPIC = "order-shipped";
	
	public void sendOrderShipped(String orderId) {

        OrderShippedEvent event = new OrderShippedEvent(orderId);

        kafkaTemplate.send(TOPIC, event);

        System.out.println("📤 Sent OrderShippedEvent: " + event);
    }
}
