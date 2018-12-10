package com.whizit.accent.billing;

public class BillNotFoundException extends RuntimeException {
	BillNotFoundException(String id) {
		super("could not find bill " + id);
	}
}
