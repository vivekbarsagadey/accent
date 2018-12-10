package com.whizit.accent.userpracticesession;

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
@RequestMapping("/api/userpracticesession")
public class UserPracticeSessionController {

	private final UserPracticeSessionRepository userPracticeSessionRepository;

	public UserPracticeSessionController(UserPracticeSessionRepository userPracticeSessionRepository) {
		this.userPracticeSessionRepository = userPracticeSessionRepository;
	}

	@GetMapping("/userPracticeSessions")
	List<UserPracticeSession> getAll() {
		return userPracticeSessionRepository.findAll();
	}

	@PostMapping("/userPracticeSessions")
	UserPracticeSession newUserPracticeSession(@RequestBody UserPracticeSession newUserPracticeSession) {
		return userPracticeSessionRepository.save(newUserPracticeSession);
	}

	@GetMapping("/userPracticeSessions/{id}")
	Optional<UserPracticeSession> findUserPracticeSessionById(@PathVariable String id) {
		Optional<UserPracticeSession> userPracticeSession = userPracticeSessionRepository.findById(id);
		if (!userPracticeSession.isPresent()) {
			throw new UserPracticeSessionNotFoundException(id);
		}
		return userPracticeSession;
	}

	@PutMapping("/userPracticeSessions/{id}")
	UserPracticeSession replaceUserPracticeSession(@RequestBody UserPracticeSession newUserPracticeSession,
			@PathVariable(name = "id", required = true) String id) {
		return userPracticeSessionRepository.findById(id).map(userPracticeSession -> {
			userPracticeSession.setUserPracticeSessionId(newUserPracticeSession.getUserPracticeSessionId());
			userPracticeSession.setUserId(newUserPracticeSession.getUserId());
			userPracticeSession.setWordId(newUserPracticeSession.getWordId());
			userPracticeSession.setScore(newUserPracticeSession.getScore());
			userPracticeSession.setAudioRecordingUrl(newUserPracticeSession.getAudioRecordingUrl());
			userPracticeSession.setSessionStartTime(newUserPracticeSession.getSessionStartTime());
			userPracticeSession.setSessionEndTime(newUserPracticeSession.getSessionEndTime());
			return userPracticeSessionRepository.save(userPracticeSession);
		}).orElseGet(() -> {
			newUserPracticeSession.setId(id);
			return userPracticeSessionRepository.save(newUserPracticeSession);
		});

	}

	@DeleteMapping("/userPracticeSessions/{id}")
	String deleteUserPracticeSession(@PathVariable String id) {
		userPracticeSessionRepository.deleteById(id);
		return "User Membership deleted successfully!!";
	}

}