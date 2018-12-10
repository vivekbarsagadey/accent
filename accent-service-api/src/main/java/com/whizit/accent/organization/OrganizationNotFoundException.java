package com.whizit.accent.organization;

public class OrganizationNotFoundException extends RuntimeException {
	OrganizationNotFoundException(String id) {
		super("could not find Organization " + id);
	}
}
