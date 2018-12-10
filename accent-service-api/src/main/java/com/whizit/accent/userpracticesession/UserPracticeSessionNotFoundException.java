package com.whizit.accent.userpracticesession;

public class UserPracticeSessionNotFoundException extends RuntimeException {
	UserPracticeSessionNotFoundException(String id) {
		super("could not find user practice session " + id);
	}
}
