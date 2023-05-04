package com.itworks.billsmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.itworks.billsmanager.model.Bill;
import com.itworks.billsmanager.model.BillStatus;
import com.itworks.billsmanager.repository.Bills;
import com.itworks.billsmanager.repository.filter.BillFilter;

@Service
public class BillingRegisterService {
	
	@Autowired
	private Bills bills;
	
	public void save(Bill bill) {
		try {
			bills.save(bill);			
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Wrong date format");
		}
	}

	public void delete(Long code) {
		bills.deleteById(code);
	}

	public String pay(Long code) {
		Optional<Bill> bill = bills.findById(code);	
		bill.get().setStatus(BillStatus.PAID);
		bills.save(bill.get());
		
		return BillStatus.PAID.getDescription();
	}
	
	public List<Bill> filter(BillFilter filter) {
		String description = filter.getDescription() == null ? "" : filter.getDescription();
		return bills.findByDescriptionContaining(description);
	}
}
