package com.whizit.accent.user;

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
@RequestMapping("/api/users")
public class UserController {

	private final UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/")
	List<User> getAll() {
		return userRepository.findAll();
	}

	@PostMapping("/")
	User newUser(@RequestBody User newUser) {
		return userRepository.save(newUser);
	}

	@GetMapping("/{id}")
	Optional<User> findUserById(@PathVariable String id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException(id);
		}
		return user;
	}

	@PutMapping("/{id}")
	User replaceUser(@RequestBody User newUser, @PathVariable(name = "id", required = true) String id) {
		return userRepository.findById(id).map(user -> {
			user.setLogin(newUser.getLogin());
			user.setUserId(newUser.getUserId());
			user.setPassword(newUser.getPassword());
			user.setFirstName(newUser.getFirstName());
			user.setLastName(newUser.getLastName());
			user.setEmailId(newUser.getEmailId());
			user.setPhone(newUser.getPhone());
			user.setRole(newUser.getRole());
			return userRepository.save(user);
		}).orElseGet(() -> {
			newUser.setId(id);
			return userRepository.save(newUser);
		});

	}

	@DeleteMapping("/{id}")
	String deleteUser(@PathVariable String id) {
		userRepository.deleteById(id);
		return "User deleted successfully!!";
	}

}