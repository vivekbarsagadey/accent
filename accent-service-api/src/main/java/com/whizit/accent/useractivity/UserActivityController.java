package com.whizit.accent.useractivity;

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
@RequestMapping("/api/useractivity")
public class UserActivityController {

	private final UserActivityRepository userActivityRepository;

	public UserActivityController(UserActivityRepository userActivityRepository) {
		this.userActivityRepository = userActivityRepository;
	}

	@GetMapping("/userActivities")
	List<UserActivity> getAll() {
		return userActivityRepository.findAll();
	}

	@PostMapping("/userActivities")
	UserActivity newUserActivity(@RequestBody UserActivity newUserActivity) {
		return userActivityRepository.save(newUserActivity);
	}

	@GetMapping("/userActivities/{id}")
	Optional<UserActivity> findUserActivityById(@PathVariable String id) {
		Optional<UserActivity> userActivity = userActivityRepository.findById(id);
		if (!userActivity.isPresent()) {
			throw new UserActivityNotFoundException(id);
		}
		return userActivity;
	}

	@PutMapping("/userActivities/{id}")
	UserActivity replaceUserActivity(@RequestBody UserActivity newUserActivity,
			@PathVariable(name = "id", required = true) String id) {
		return userActivityRepository.findById(id).map(userActivity -> {
			userActivity.setUseractivityId(newUserActivity.getUseractivityId());
			userActivity.setUserId(newUserActivity.getUserId());
			userActivity.setUserActivityTime(newUserActivity.getUserActivityTime());
			userActivity.setUserActivityType(newUserActivity.getUserActivityType());
			return userActivityRepository.save(userActivity);
		}).orElseGet(() -> {
			newUserActivity.setId(id);
			return userActivityRepository.save(newUserActivity);
		});

	}

	@DeleteMapping("/userActivities/{id}")
	String deleteUserActivity(@PathVariable String id) {
		userActivityRepository.deleteById(id);
		return "UserActivity deleted successfully!!";
	}

}