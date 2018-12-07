package com.whizit.accent.user;

public class UserNotFoundException extends RuntimeException {
	UserNotFoundException(String id) {
		super("could not find user " + id);
	}
}
