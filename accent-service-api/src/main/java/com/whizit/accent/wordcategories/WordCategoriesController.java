package com.whizit.accent.wordcategories;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wordcategories")
public class WordCategoriesController {

	private final WordCategoriesRepository wordCategoriesRepo;

	public WordCategoriesController(WordCategoriesRepository wordCategoriesRepo) {
		this.wordCategoriesRepo = wordCategoriesRepo;
	}

	@GetMapping("/")
	ResponseEntity<List<WordCategories>> getAll() {
		return new ResponseEntity<List<WordCategories>>(wordCategoriesRepo.findAll(), HttpStatus.OK);
	}

	@PostMapping("/")
	ResponseEntity<WordCategories> newWordCategory(@RequestBody WordCategories newWordCategory) {
		return new ResponseEntity<WordCategories>(wordCategoriesRepo.save(newWordCategory), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	ResponseEntity<WordCategories> findWordCategoryById(@PathVariable String id) {
		final var response = new ResponseEntity<WordCategories>(HttpStatus.OK);
		wordCategoriesRepo.findById(id).ifPresentOrElse(u -> {

		}, () -> {
			response.status(HttpStatus.NOT_FOUND);
		});
		return response;
	}

	@PutMapping("/{id}")
	ResponseEntity<WordCategories> replaceWordCategory(@RequestBody WordCategories newWordCategory,
			@PathVariable(name = "id", required = true) String id) {
		return wordCategoriesRepo.findById(id).map(wordCategory -> {
			wordCategory.setWordCategoryId(newWordCategory.getWordCategoryId());
			wordCategory.setWordCategoryName(newWordCategory.getWordCategoryName());
			return new ResponseEntity<WordCategories>(wordCategoriesRepo.save(wordCategory), HttpStatus.OK);

		}).orElseGet(() -> {
			newWordCategory.setId(id);
			return new ResponseEntity<WordCategories>(wordCategoriesRepo.save(newWordCategory), HttpStatus.OK);
		});

	}

	@DeleteMapping("/{id}")
	ResponseEntity<String> deleteCategory(@PathVariable String id) {
		wordCategoriesRepo.deleteById(id);
		return new ResponseEntity<String>("category deleted successfully!!", HttpStatus.OK);
	}

}