package com.whizit.accent.word;

public class WordNotFoundException extends RuntimeException {
	WordNotFoundException(String id) {
		super("could not find word " + id);
	}
}
