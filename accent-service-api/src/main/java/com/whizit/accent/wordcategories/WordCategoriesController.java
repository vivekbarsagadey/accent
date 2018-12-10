package com.whizit.accent.wordcategories;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wordcategory")
public class WordCategoriesController {

	private final WordCategoriesRepository wordCategoriesRepo;

	public WordCategoriesController(WordCategoriesRepository wordCategoriesRepo) {
		this.wordCategoriesRepo = wordCategoriesRepo;
	}

	@GetMapping("/wordCategory")
	List<WordCategories> getAll() {
		return wordCategoriesRepo.findAll();
	}

	@PostMapping("/wordCategory")
	WordCategories newWordCategory(@RequestBody WordCategories newWordCategory) {
		return wordCategoriesRepo.save(newWordCategory);
	}

	@GetMapping("/wordCategory/{id}")
	Optional<WordCategories> findWordCategoryById(@PathVariable String id) {
		Optional<WordCategories> wordCategory = wordCategoriesRepo.findById(id);
		if (!wordCategory.isPresent()) {
			throw new WordCategoriesNotFoundException(id);
		}
		return wordCategory;
	}

	@PutMapping("/wordCategory/{id}")
	WordCategories replaceWordCategory(@RequestBody WordCategories newWordCategory,
			@PathVariable(name = "id", required = true) String id) {
		return wordCategoriesRepo.findById(id).map(wordCategory -> {
			wordCategory.setWordCategoryId(newWordCategory.getWordCategoryId());
			wordCategory.setWordCategoryName(newWordCategory.getWordCategoryName());
			return wordCategoriesRepo.save(wordCategory);

		}).orElseGet(() -> {
			newWordCategory.setId(id);
			return wordCategoriesRepo.save(newWordCategory);
		});

	}

	@DeleteMapping("/wordCategory/{id}")
	String deleteCategory(@PathVariable String id) {
		wordCategoriesRepo.deleteById(id);
		return "category deleted successfully!!";
	}

}