package com.whizit.accent.billing;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/bill")
public class BillingController {

	private final BillingRepository billingRepository;

	public BillingController(BillingRepository billingRepository) {
		this.billingRepository = billingRepository;
	}

	@GetMapping("/bills")
	List<Billing> getAll() {
		return billingRepository.findAll();
	}

	@PostMapping("/Bills")
	Billing newBill(@RequestBody Billing newBill) {
		return billingRepository.save(newBill);
	}

	@GetMapping("/Bills/{id}")
	Optional<Billing> findBillById(@PathVariable String id) {
		Optional<Billing> bill = billingRepository.findById(id);
		if (!bill.isPresent()) {
			throw new BillNotFoundException(id);
		}
		return bill;
	}

	@PutMapping("/Bills/{id}")
	Billing replaceBill(@RequestBody Billing newBill, @PathVariable(name = "id", required = true) String id) {
		return billingRepository.findById(id).map(bill -> {
			bill.setName(newBill.getName());

			return billingRepository.save(bill);
		}).orElseGet(() -> {
			newBill.setId(id);
			return billingRepository.save(newBill);
		});

	}

	@DeleteMapping("/Bills/{id}")
	String deleteBill(@PathVariable String id) {
		billingRepository.deleteById(id);
		return "Billing deleted successfully!!";
	}

}
