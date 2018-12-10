package com.whizit.accent.useractivity;

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
@RequestMapping("/api/useractivities")
public class UserActivityController {

	private final UserActivityRepository userActivityRepository;

	public UserActivityController(UserActivityRepository userActivityRepository) {
		this.userActivityRepository = userActivityRepository;
	}

	@GetMapping("/")
	ResponseEntity<List<UserActivity>> getAll() {
		return new ResponseEntity<List<UserActivity>>(userActivityRepository.findAll(), HttpStatus.OK);
	}

	@PostMapping("/")
	ResponseEntity<UserActivity> newUserActivity(@RequestBody UserActivity newUserActivity) {
		return new ResponseEntity<UserActivity>(userActivityRepository.save(newUserActivity), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	ResponseEntity<UserActivity> findUserActivityById(@PathVariable String id) {
		final var response = new ResponseEntity<UserActivity>(HttpStatus.OK);
		userActivityRepository.findById(id).ifPresentOrElse(U -> {

		}, () -> {
			response.status(HttpStatus.NOT_FOUND);
		});
		return response;
	}

	@PutMapping("/{id}")
	ResponseEntity<UserActivity> replaceUserActivity(@RequestBody UserActivity newUserActivity,
			@PathVariable(name = "id", required = true) String id) {
		return userActivityRepository.findById(id).map(userActivity -> {
			userActivity.setUseractivityId(newUserActivity.getUseractivityId());
			userActivity.setUserId(newUserActivity.getUserId());
			userActivity.setUserActivityTime(newUserActivity.getUserActivityTime());
			userActivity.setUserActivityType(newUserActivity.getUserActivityType());
			return new ResponseEntity<UserActivity>(userActivityRepository.save(userActivity), HttpStatus.OK);
		}).orElseGet(() -> {
			newUserActivity.setId(id);
			return new ResponseEntity<UserActivity>(userActivityRepository.save(newUserActivity), HttpStatus.NOT_FOUND);
		});

	}

	@DeleteMapping("/{id}")
	ResponseEntity<String> deleteUserActivity(@PathVariable String id) {
		userActivityRepository.deleteById(id);
		return new ResponseEntity<String>("UserActivity deleted successfully!!", HttpStatus.OK);
	}

}