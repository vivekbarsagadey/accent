package com.whizit.accent.billing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/bill")
public class BillingController {

	private final BillingRepository billingRepository;

	public BillingController(BillingRepository billingRepository) {
		this.billingRepository = billingRepository;
	}

}
