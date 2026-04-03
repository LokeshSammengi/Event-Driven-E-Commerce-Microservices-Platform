package com.project.inventory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "FailedEvents")
@Data
public class FailedEvent {

	@Id
    private String orderId;

    private String payload;
    private String errorMessage;
    private String status; // FAILED / RETRIED
    
}
