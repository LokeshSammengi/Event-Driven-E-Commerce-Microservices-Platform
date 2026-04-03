package com.project.inventory.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.project.events.StockFailedEvent;
import com.project.events.StockReservedEvent;

@Service
public class InventoryProducer {

	@Autowired
	private KafkaTemplate<String,Object> kafkaTemplate;
	
	public static final String TOPIC1 = "stock-reserved";
	public static final String TOPIC2 = "stock-failed";
	
	
	public void sendStockReserved(StockReservedEvent stockReservedEvent) {
		kafkaTemplate.send(TOPIC1, stockReservedEvent);
		System.out.println("stock are available for order id "+ stockReservedEvent.getOrderId());
	}
	
	public void sendStockFailed(StockFailedEvent stockFailedEvnet) {
		kafkaTemplate.send(TOPIC2, stockFailedEvnet);
		System.out.println("stock are not available for order id :"+ stockFailedEvnet.getOrderId());
	}
}
