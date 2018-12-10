package com.whizit.accent.useractivity;

public class UserActivityNotFoundException extends RuntimeException {
	UserActivityNotFoundException(String id) {
		super("could not find user activity " + id);
	}
}
