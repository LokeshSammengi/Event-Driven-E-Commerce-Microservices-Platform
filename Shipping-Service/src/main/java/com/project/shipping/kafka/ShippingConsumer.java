package com.project.shipping.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.project.events.StockFailedEvent;
import com.project.events.StockReservedEvent;
import com.project.shipping.service.ShippingProducer;

@Service
public class ShippingConsumer {
	
	@Autowired
	private ShippingProducer producer;

	@KafkaListener(topics ="stock-reserved" ,groupId ="shipping-group" )	
	public void InventoryConsumerEvent(StockReservedEvent event) {
		System.out.println("Recived stock event :"+event);
		
		// Simulate shipment creation
        System.out.println("🚚 Creating shipment for order: " + event.getOrderId());
        
        //send next event
		producer.sendOrderShipped(event.getOrderId());
	
	}
	
	
}
