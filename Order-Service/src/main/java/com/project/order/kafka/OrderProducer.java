package com.project.order.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.project.events.OrderCreatedEvent;
import com.project.events.OrderShippedEvent;
import com.project.events.StockFailedEvent;

@Service
public class OrderProducer {

	@Autowired
	private KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

	public final static String TOPIC = "order-created";

	public void createOrderEvent(OrderCreatedEvent orderevent) {
		kafkaTemplate.send(TOPIC, orderevent);
		System.out.println("order placed event sent :" + orderevent);
	}

	@KafkaListener(topics = "order-shipped", groupId = "order-group")
	public void handleOrderShipped(OrderShippedEvent event) {

		System.out.println("📥 Received OrderShippedEvent: " + event);

		// Update status → COMPLETED
		System.out.println("✅ Order COMPLETED: " + event.getOrderId());
	}

	@KafkaListener(topics = "stock-failed", groupId = "order-group")
	public void handleStockFailed(StockFailedEvent event) {
		System.out.println("📥 Received StockFailedEvent: " + event);

		// Update status → CANCELLED
		System.out.println("❌ Order CANCELLED: " + event.getOrderId());

	}

}
