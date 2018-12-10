package com.whizit.accent.user;

import java.util.List;

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

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/")
	@ApiOperation(value = "Get All users", produces = "application/text")
	public ResponseEntity<List<User>> findAll() {
		return new ResponseEntity<List<User>>(userRepository.findAll(), HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<User> save(@RequestBody User newUser) {
		return new ResponseEntity<User>((userRepository.save(newUser)) ,HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable String id) {
		final var response = new ResponseEntity<User>(HttpStatus.OK);
		userRepository.findById(id).ifPresentOrElse(u->{
			response.ok(u);
		}, ()->{
			response.status(HttpStatus.NOT_FOUND);
		});
		return response;
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> update(@RequestBody User newUser, @PathVariable(name = "id", required = true) String id) {
		return userRepository.findById(id).map(user -> {
			user.setLogin(newUser.getLogin());
			user.setUserId(newUser.getUserId());
			user.setPassword(newUser.getPassword());
			user.setFirstName(newUser.getFirstName());
			user.setLastName(newUser.getLastName());
			user.setEmailId(newUser.getEmailId());
			user.setPhone(newUser.getPhone());
			user.setRole(newUser.getRole());
			return new ResponseEntity<User>(userRepository.save(user),HttpStatus.OK);
		}).orElseGet(() -> {
			return new ResponseEntity<User>(newUser,HttpStatus.NOT_FOUND);
		});

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable String id) {
		userRepository.deleteById(id);
		return  new ResponseEntity<String>("User deleted successfully!!",HttpStatus.OK);
	}

}