package com.whizit.accent.userpracticesession;

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
@RequestMapping("/api/userpracticesessions")
public class UserPracticeSessionController {

	private final UserPracticeSessionRepository userPracticeSessionRepository;

	public UserPracticeSessionController(UserPracticeSessionRepository userPracticeSessionRepository) {
		this.userPracticeSessionRepository = userPracticeSessionRepository;
	}

	@GetMapping("/")
	ResponseEntity<List<UserPracticeSession>> getAll() {
		return new ResponseEntity<List<UserPracticeSession>>(userPracticeSessionRepository.findAll(), HttpStatus.OK);
	}

	@PostMapping("/")
	ResponseEntity<UserPracticeSession> newUserPracticeSession(
			@RequestBody UserPracticeSession newUserPracticeSession) {
		return new ResponseEntity<UserPracticeSession>(userPracticeSessionRepository.save(newUserPracticeSession),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	ResponseEntity<UserPracticeSession> findUserPracticeSessionById(@PathVariable String id) {
		final var response = new ResponseEntity<UserPracticeSession>(HttpStatus.OK);
		userPracticeSessionRepository.findById(id).ifPresentOrElse(u -> {

		}, () -> {
			response.status(HttpStatus.NOT_FOUND);
		});
		return response;
	}

	@PutMapping("/{id}")
	ResponseEntity<UserPracticeSession> replaceUserPracticeSession(
			@RequestBody UserPracticeSession newUserPracticeSession,
			@PathVariable(name = "id", required = true) String id) {
		return userPracticeSessionRepository.findById(id).map(userPracticeSession -> {
			userPracticeSession.setUserPracticeSessionId(newUserPracticeSession.getUserPracticeSessionId());
			userPracticeSession.setUserId(newUserPracticeSession.getUserId());
			userPracticeSession.setWordId(newUserPracticeSession.getWordId());
			userPracticeSession.setScore(newUserPracticeSession.getScore());
			userPracticeSession.setAudioRecordingUrl(newUserPracticeSession.getAudioRecordingUrl());
			userPracticeSession.setSessionStartTime(newUserPracticeSession.getSessionStartTime());
			userPracticeSession.setSessionEndTime(newUserPracticeSession.getSessionEndTime());
			return new ResponseEntity<UserPracticeSession>(userPracticeSessionRepository.save(userPracticeSession),
					HttpStatus.OK);
		}).orElseGet(() -> {
			newUserPracticeSession.setId(id);
			return new ResponseEntity<UserPracticeSession>(userPracticeSessionRepository.save(newUserPracticeSession),
					HttpStatus.OK);
		});

	}

	@DeleteMapping("/{id}")
	ResponseEntity<String> deleteUserPracticeSession(@PathVariable String id) {
		userPracticeSessionRepository.deleteById(id);
		return new ResponseEntity<String>("User Membership deleted successfully!!", HttpStatus.OK);
	}

}