package com.whizit.accent.wordcategories;

public class WordCategoriesNotFoundException extends RuntimeException {
	WordCategoriesNotFoundException(String id) {
		super("could not find word category " + id);
	}
}
