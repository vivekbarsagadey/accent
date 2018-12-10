package com.whizit.accent.userhistory;

public class UserHistoryNotFoundException extends RuntimeException {
	UserHistoryNotFoundException(String id) {
		super("could not find user history " + id);
	}
}
