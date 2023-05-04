package com.itworks.billsmanager.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itworks.billsmanager.model.Bill;
import com.itworks.billsmanager.model.BillStatus;
import com.itworks.billsmanager.repository.Bills;
import com.itworks.billsmanager.repository.filter.BillFilter;
import com.itworks.billsmanager.service.BillingRegisterService;

@Controller
@RequestMapping("/bills")
public class BillingController {
	
	private static final String BILL_REGISTER_VIEW = "BillRegister";
	@Autowired
	private Bills bills;
	
	@Autowired
	private BillingRegisterService billingRegisterService;
	
	@GetMapping("/register")
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView(BILL_REGISTER_VIEW);
		mv.addObject(new Bill());
		return mv;
	}
	
	@PostMapping
	public ModelAndView save(@Validated Bill bill, Errors errors, RedirectAttributes attributes) {
		
		ModelAndView mv = new ModelAndView(BILL_REGISTER_VIEW);
		
		if(errors.hasErrors()) {
			return mv;
		}
		
		try {
			billingRegisterService.save(bill);
			
			ModelAndView mvAfterSave = new ModelAndView("redirect:/bills/register");
			attributes.addFlashAttribute("message", "Bill '" + bill.getDescription() + "' registered successfully!");
			return mvAfterSave; // It could just return a string with the redirect URL
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dueDate", null, e.getMessage());
			return mv;			
		}
	}
	
	@RequestMapping
	public ModelAndView search(@ModelAttribute("filter") BillFilter filter) {
		
		List<Bill> allBills = billingRegisterService.filter(filter);
		ModelAndView mv = new ModelAndView("BillSearch");
		mv.addObject("allBills", allBills);
		return mv;
	}
	
	@GetMapping(value="{code}")
	public ModelAndView edit(@PathVariable Long code) {
		Optional<Bill> bill = bills.findById(code);
		ModelAndView mv = new ModelAndView(BILL_REGISTER_VIEW);
		mv.addObject(bill.get());
		return mv;
	}
	
	@RequestMapping(value="{code}", method=RequestMethod.POST)
	public String delete(@PathVariable Long code, RedirectAttributes attributes) {
		billingRegisterService.delete(code);
		attributes.addFlashAttribute("message", "Bill deleted successfully!");
		return "redirect:/bills";
	}
	
	@ModelAttribute("allBillStatuses")
	public List<BillStatus> allBillStatuses() {
		return Arrays.asList(BillStatus.values());
	}
	
	@PutMapping(value="/{code}/receive")
	public @ResponseBody String receive(@PathVariable Long code) {
		return billingRegisterService.receive(code);
	}
}
