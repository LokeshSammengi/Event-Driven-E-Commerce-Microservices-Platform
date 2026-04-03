package com.project.inventory.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.inventory.entity.FailedEvent;

public interface FailedInventoryRepo extends JpaRepository<FailedEvent,String> {

}
