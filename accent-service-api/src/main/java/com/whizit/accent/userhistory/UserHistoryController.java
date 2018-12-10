package com.whizit.accent.userhistory;

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
@RequestMapping("/api/userhistory")
public class UserHistoryController {

	private final UserHistoryRepository userHistoryRepository;

	public UserHistoryController(UserHistoryRepository userHistoryRepository) {
		this.userHistoryRepository = userHistoryRepository;
	}

	@GetMapping("/userHistories")
	List<UserHistory> getAll() {
		return userHistoryRepository.findAll();
	}

	@PostMapping("/userHistories")
	UserHistory newUserHistory(@RequestBody UserHistory newUserHistory) {
		return userHistoryRepository.save(newUserHistory);
	}

	@GetMapping("/userHistories/{id}")
	Optional<UserHistory> findUserHistoryById(@PathVariable String id) {
		Optional<UserHistory> userHistory = userHistoryRepository.findById(id);
		if (!userHistory.isPresent()) {
			throw new UserHistoryNotFoundException(id);
		}
		return userHistory;
	}

	@PutMapping("/userHistories/{id}")
	UserHistory replaceUserHistory(@RequestBody UserHistory newUserHistory,
			@PathVariable(name = "id", required = true) String id) {
		return userHistoryRepository.findById(id).map(userHistory -> {
			userHistory.setId(newUserHistory.getId());
			// need to add field as there are no attributes in UserHistory entity

			return userHistoryRepository.save(userHistory);
		}).orElseGet(() -> {
			newUserHistory.setId(id);
			return userHistoryRepository.save(newUserHistory);
		});

	}

	@DeleteMapping("/userHistories/{id}")
	String deleteUserHistory(@PathVariable String id) {
		userHistoryRepository.deleteById(id);
		return "UserHistory deleted successfully!!";
	}

}