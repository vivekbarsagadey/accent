package com.whizit.accent.usermembership;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usermembership")
public class UserMembershipController {

	private final UserMembershipRepository userMembershipRepository;

	public UserMembershipController(UserMembershipRepository userMembershipRepository) {
		this.userMembershipRepository = userMembershipRepository;
	}

	@GetMapping("/userMemberships")
	List<UserMembership> getAll() {
		return userMembershipRepository.findAll();
	}

	@PostMapping("/userMemberships")
	UserMembership newUserMembership(@RequestBody UserMembership newUserMembership) {
		return userMembershipRepository.save(newUserMembership);
	}

	@GetMapping("/userMemberships/{id}")
	Optional<UserMembership> findUserMembershipById(@PathVariable String id) {
		Optional<UserMembership> userMembership = userMembershipRepository.findById(id);
		if (!userMembership.isPresent()) {
			throw new UserMembershipNotFoundException(id);
		}
		return userMembership;
	}

	@PutMapping("/userMemberships/{id}")
	UserMembership replaceUserMembership(@RequestBody UserMembership newUserMembership,
			@PathVariable(name = "id", required = true) String id) {
		return userMembershipRepository.findById(id).map(userMembership -> {
			userMembership.setUserMembershipId(newUserMembership.getUserMembershipId());
			userMembership.setMembershipId(newUserMembership.getMembershipId());
			userMembership.setUserId(newUserMembership.getUserId());
			userMembership.setUserMembershipStartDate(newUserMembership.getUserMembershipStartDate());
			userMembership.setUserMembershipEndDate(newUserMembership.getUserMembershipEndDate());
			userMembership.setAmountPaid(newUserMembership.getAmountPaid());
			userMembership.setPaymentDate(newUserMembership.getPaymentDate());
			userMembership.setPaymentMode(newUserMembership.getPaymentMode());
			userMembership.setUserMembershipStatus(newUserMembership.getUserMembershipStatus());
			return userMembershipRepository.save(userMembership);
		}).orElseGet(() -> {
			newUserMembership.setId(id);
			return userMembershipRepository.save(newUserMembership);
		});

	}

	@DeleteMapping("/userMemberships/{id}")
	String deleteUserMembership(@PathVariable String id) {
		userMembershipRepository.deleteById(id);
		return "User Membership deleted successfully!!";
	}

}