package com.project.inventory.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.project.events.OrderCreatedEvent;
import com.project.events.StockFailedEvent;
import com.project.events.StockReservedEvent;
import com.project.inventory.producer.InventoryProducer;

//this is listener bcoz from order produces and sends event to the inventory which consumer and verifies 
@Service
public class InventoryConsumer {
	
	@Autowired
	private InventoryProducer producer;

	@KafkaListener(topics = "order-created",groupId ="inventory-group")
	public void orderConsumerEvent(OrderCreatedEvent event) {
		
		System.out.println("📥 Received Order: " + event);
		int quantity = event.getQuantity();
		
		if(quantity <= 5) {
			//here another event will be created - stock reserved
			StockReservedEvent stockreserveredevent = new StockReservedEvent(event.getOrderId(),event.getProductId(), quantity);
			producer.sendStockReserved(stockreserveredevent);
			System.out.println("stock available for order id : "+ event.getOrderId());
		}
		else {
			//here another event will be created - stock failed
			StockFailedEvent stockfailedevent= new StockFailedEvent(event.getOrderId(), "less quantity....");
			System.out.println("stock not available for order id : "+event.getOrderId());
		}
		
	}
	
}
