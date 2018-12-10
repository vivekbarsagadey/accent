package com.whizit.accent.membership;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/memberships")
public class MembershipController {

	private final MembershipRepository membershipRepository;

	public MembershipController(MembershipRepository membershipRepository) {
		this.membershipRepository = membershipRepository;
	}

	@GetMapping("/")
	ResponseEntity<List<Membership>> getAll() {
		return new ResponseEntity<List<Membership>>(membershipRepository.findAll(), HttpStatus.OK);
	}

	@PostMapping("/")
	ResponseEntity<Membership> newMembership(@RequestBody Membership newMembership) {
		return new ResponseEntity<Membership>(membershipRepository.save(newMembership), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	ResponseEntity<Membership> findMembershipById(@PathVariable String id) {
		final var response = new ResponseEntity<Membership>(HttpStatus.OK);
		membershipRepository.findById(id).ifPresentOrElse(u -> {

		}, () -> {
			response.status(HttpStatus.NOT_FOUND);
		});
		return response;
	}

	@PutMapping("/{id}")
	ResponseEntity<Membership> replaceMembership(@RequestBody Membership newMembership,
			@PathVariable(name = "id", required = true) String id) {
		return membershipRepository.findById(id).map(membership -> {
			membership.setName(newMembership.getName());
			membership.setPeriod(newMembership.getPeriod());
			membership.setFee(newMembership.getFee());
			membership.setDetails(newMembership.getDetails());
			membership.setType(newMembership.getDiscount());
			membership.setDiscountPeriod(newMembership.getDiscountPeriod());
			return new ResponseEntity<Membership>(membershipRepository.save(membership), HttpStatus.OK);
		}).orElseGet(() -> {
			newMembership.setId(id);
			return new ResponseEntity<Membership>(membershipRepository.save(newMembership), HttpStatus.NOT_FOUND);
		});

	}

	@DeleteMapping("/{id}")
	ResponseEntity<String> deleteMembership(@PathVariable String id) {
		membershipRepository.deleteById(id);
		return new ResponseEntity<String>("Membership deleted successfully!!", HttpStatus.OK);
	}

}