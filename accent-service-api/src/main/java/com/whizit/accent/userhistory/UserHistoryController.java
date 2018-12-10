package com.whizit.accent.userhistory;

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
@RequestMapping("/api/userhistories")
public class UserHistoryController {

	private final UserHistoryRepository userHistoryRepository;

	public UserHistoryController(UserHistoryRepository userHistoryRepository) {
		this.userHistoryRepository = userHistoryRepository;
	}

	@GetMapping("/")
	ResponseEntity<List<UserHistory>> getAll() {
		return new ResponseEntity<List<UserHistory>>(userHistoryRepository.findAll(), HttpStatus.OK);
	}

	@PostMapping("/")
	ResponseEntity<UserHistory> newUserHistory(@RequestBody UserHistory newUserHistory) {
		return new ResponseEntity<UserHistory>(userHistoryRepository.save(newUserHistory), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	ResponseEntity<UserHistory> findUserHistoryById(@PathVariable String id) {
		final var response = new ResponseEntity<UserHistory>(HttpStatus.OK);
		userHistoryRepository.findById(id).ifPresentOrElse(u -> {

		}, () -> {
			response.status(HttpStatus.NOT_FOUND);
		});
		return response;
	}

	@PutMapping("/{id}")
	ResponseEntity<UserHistory> replaceUserHistory(@RequestBody UserHistory newUserHistory,
			@PathVariable(name = "id", required = true) String id) {
		return userHistoryRepository.findById(id).map(userHistory -> {
			userHistory.setId(newUserHistory.getId());
			// need to add field as there are no attributes in UserHistory entity

			return new ResponseEntity<UserHistory>(userHistoryRepository.save(userHistory), HttpStatus.OK);
		}).orElseGet(() -> {
			newUserHistory.setId(id);
			return new ResponseEntity<UserHistory>(userHistoryRepository.save(newUserHistory), HttpStatus.OK);
		});

	}

	@DeleteMapping("/{id}")
	ResponseEntity<String> deleteUserHistory(@PathVariable String id) {
		userHistoryRepository.deleteById(id);
		return new ResponseEntity<String>("UserHistory deleted successfully!!",HttpStatus.OK);
	}

}