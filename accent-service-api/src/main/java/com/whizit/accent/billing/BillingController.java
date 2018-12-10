package com.whizit.accent.billing;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/bills")
public class BillingController {

	private final BillingRepository billingRepository;

	public BillingController(BillingRepository billingRepository) {
		this.billingRepository = billingRepository;
	}

	@GetMapping("/")
	@ApiOperation(value = "Get All Bills", produces = "application/text")
	ResponseEntity<List<Billing>> getAll() {
		return new ResponseEntity<List<Billing>>(billingRepository.findAll(), HttpStatus.OK);
	}

	@PostMapping("/")
	ResponseEntity<Billing> newBill(@RequestBody Billing newBill) {
		return new ResponseEntity<Billing>(billingRepository.save(newBill), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	ResponseEntity<Billing> findBillById(@PathVariable String id) {
		final var response = new ResponseEntity<Billing>(HttpStatus.OK);
		billingRepository.findById(id).ifPresentOrElse(u -> {
			response.ok(u);
		}, () -> {
			response.status(HttpStatus.NOT_FOUND);
		});
		return response;
	}

	@PutMapping("/{id}")
	ResponseEntity<Billing> replaceBill(@RequestBody Billing newBill,
			@PathVariable(name = "id", required = true) String id) {
		return billingRepository.findById(id).map(bill -> {
			bill.setName(newBill.getName());

			return new ResponseEntity<Billing>(billingRepository.save(bill), HttpStatus.OK);

		}).orElseGet(() -> {
			newBill.setId(id);
			return new ResponseEntity<Billing>(newBill, HttpStatus.NOT_FOUND);
		});

	}

	@DeleteMapping("/{id}")
	ResponseEntity<String> deleteBill(@PathVariable String id) {
		billingRepository.deleteById(id);
		return new ResponseEntity<String>("Bill deleted successfully!!", HttpStatus.OK);
	}

}
