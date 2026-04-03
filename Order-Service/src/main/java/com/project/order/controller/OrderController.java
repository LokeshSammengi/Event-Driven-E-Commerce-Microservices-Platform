package com.project.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.order.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService service;
	
	@PostMapping("/placeevent")
	public ResponseEntity<String> placeOrder(@RequestParam String productId, 
												@RequestParam int quantity){
		
		String result =service.createOrder(productId, quantity);
		return new ResponseEntity<String>(result, HttpStatus.OK);		
	}
}
