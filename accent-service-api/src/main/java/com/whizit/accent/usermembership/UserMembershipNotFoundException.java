package com.whizit.accent.usermembership;

public class UserMembershipNotFoundException extends RuntimeException {
	UserMembershipNotFoundException(String id) {
		super("could not find user membership " + id);
	}
}
