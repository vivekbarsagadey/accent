package com.whizit.accent.membership;

public class MembershipNotFoundException extends RuntimeException {
	MembershipNotFoundException(String id) {
		super("could not find Membership " + id);
	}
}
