package com.project.inventory.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.project.events.OrderCreatedEvent;
import com.project.events.StockFailedEvent;
import com.project.events.StockReservedEvent;
import com.project.inventory.entity.FailedEvent;
import com.project.inventory.producer.InventoryProducer;
import com.project.inventory.repo.FailedInventoryRepo;

//this is listener bcoz from order produces and sends event to the inventory which consumer and verifies 
@Service
public class InventoryConsumer {

	@Autowired
	private InventoryProducer producer;
	
	@Autowired
	private FailedInventoryRepo repo;

	@KafkaListener(topics = "order-created", groupId = "inventory-group")
	public void orderConsumerEvent(OrderCreatedEvent event) {

		System.out.println("📥 Received Order: " + event);
		int quantity = event.getQuantity();

		// 🔥 Force failure for testing
		if (event.getProductId().equals("FAIL")) {
			throw new RuntimeException("Simulated failure");
		}

		if (quantity <= 5) {
			// here another event will be created - stock reserved
			StockReservedEvent stockreserveredevent = new StockReservedEvent(event.getOrderId(), event.getProductId(),
					quantity);
			producer.sendStockReserved(stockreserveredevent);
			System.out.println("stock available for order id : " + event.getOrderId());
		} else {
			// here another event will be created - stock failed
			StockFailedEvent stockfailedevent = new StockFailedEvent(event.getOrderId(), "INsufficient stock");
			System.out.println("stock not available for order id : " + event.getOrderId());
		}

	}

	
	@KafkaListener(topics = "order-created-dlq", groupId = "inventory-group")
	public void consumeDLQ(OrderCreatedEvent event) {
		
		System.out.println("order-created-dlq : " + event);
		
		// Call same business logic again
		processOrder(event);
	}

	
	public void processOrder(OrderCreatedEvent event) {

		if (event.getQuantity() <= 5) {
			producer.sendStockReserved(
					new StockReservedEvent(event.getOrderId(), event.getProductId(), event.getQuantity()));
		} else {
			producer.sendStockFailed(new StockFailedEvent(event.getOrderId(), "Insufficient stock"));
		}
	}
	


	@KafkaListener(topics = "order-created-dlq", groupId = "inventory-dlq-group")
	public void consumeDlq(OrderCreatedEvent event) {

	    FailedEvent failed = new FailedEvent();
	    
	    failed.setOrderId(event.getOrderId());
	    failed.setPayload(event.toString());
	    failed.setErrorMessage("Moved to DLQ");
	    failed.setStatus("FAILED");

	    repo.save(failed);
	}
}
