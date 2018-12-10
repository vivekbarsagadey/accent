package com.whizit.accent.usermembership;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/userMemberships")
public class UserMembershipController {

	private final UserMembershipRepository userMembershipRepository;

	public UserMembershipController(UserMembershipRepository userMembershipRepository) {
		this.userMembershipRepository = userMembershipRepository;
	}

	@GetMapping("/")
	ResponseEntity<List<UserMembership>> getAll() {
		return new ResponseEntity<List<UserMembership>>(userMembershipRepository.findAll(), HttpStatus.OK);
	}

	@PostMapping("/")
	ResponseEntity<UserMembership> newUserMembership(@RequestBody UserMembership newUserMembership) {
		return new ResponseEntity<UserMembership>(userMembershipRepository.save(newUserMembership), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	ResponseEntity<UserMembership> findUserMembershipById(@PathVariable String id) {
		final var response = new ResponseEntity<UserMembership>(HttpStatus.OK);
		userMembershipRepository.findById(id).ifPresentOrElse(U -> {

		}, () -> {
			response.status(HttpStatus.NOT_FOUND);
		});
		return response;
	}

	@PutMapping("/{id}")
	ResponseEntity<UserMembership> replaceUserMembership(@RequestBody UserMembership newUserMembership,
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
			return new ResponseEntity<UserMembership>(userMembershipRepository.save(userMembership), HttpStatus.OK);
		}).orElseGet(() -> {
			newUserMembership.setId(id);
			return new ResponseEntity<UserMembership>(userMembershipRepository.save(newUserMembership), HttpStatus.OK);
		});

	}

	@DeleteMapping("/{id}")
	ResponseEntity<String> deleteUserMembership(@PathVariable String id) {
		userMembershipRepository.deleteById(id);
		return new ResponseEntity<String>("User Membership deleted successfully!!", HttpStatus.OK);
	}

}