package com.whizit.accent.membership;

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

@Controller
@RequestMapping("/api/membership")
public class MembershipController {

	private final MembershipRepository membershipRepository;

	public MembershipController(MembershipRepository membershipRepository) {
		this.membershipRepository = membershipRepository;
	}

	@GetMapping("/memberships")
	List<Membership> getAll() {
		return membershipRepository.findAll();
	}

	@PostMapping("/Memberships")
	Membership newMembership(@RequestBody Membership newMembership) {
		return membershipRepository.save(newMembership);
	}

	@GetMapping("/Memberships/{id}")
	Optional<Membership> findMembershipById(@PathVariable String id) {
		Optional<Membership> membership = membershipRepository.findById(id);
		if (!membership.isPresent()) {
			throw new MembershipNotFoundException(id);
		}
		return membership;
	}

	@PutMapping("/Memberships/{id}")
	Membership replaceMembership(@RequestBody Membership newMembership,
			@PathVariable(name = "id", required = true) String id) {
		return membershipRepository.findById(id).map(membership -> {
			membership.setName(newMembership.getName());
			membership.setPeriod(newMembership.getPeriod());
			membership.setFee(newMembership.getFee());
			membership.setDetails(newMembership.getDetails());
			membership.setType(newMembership.getDiscount());
			membership.setDiscountPeriod(newMembership.getDiscountPeriod());
			return membershipRepository.save(membership);
		}).orElseGet(() -> {
			newMembership.setId(id);
			return membershipRepository.save(newMembership);
		});

	}

	@DeleteMapping("/Memberships/{id}")
	String deleteMembership(@PathVariable String id) {
		membershipRepository.deleteById(id);
		return "Membership deleted successfully!!";
	}

}