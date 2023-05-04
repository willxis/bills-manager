package com.itworks.billsmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itworks.billsmanager.model.Bill;

public interface Bills extends JpaRepository<Bill, Long> {
	
	public List<Bill> findByDescriptionContaining(String description);

}
