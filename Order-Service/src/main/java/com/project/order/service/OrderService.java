package com.project.order.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.events.OrderCreatedEvent;
import com.project.order.kafka.OrderProducer;

@Service
public class OrderService {

	@Autowired
	private OrderProducer orderProducer;
	
	public String createOrder(String productId , int quantity ) {
		String orderId = UUID.randomUUID().toString();
		
		OrderCreatedEvent event = new OrderCreatedEvent();
		event.setOrderId(orderId);
		event.setProductId(productId);
		event.setQuantity(quantity);
		
		//send to kafka
		orderProducer.createOrderEvent(event);
		
		return "order placed successfully with orderId : " + orderId;
	}
	
}
