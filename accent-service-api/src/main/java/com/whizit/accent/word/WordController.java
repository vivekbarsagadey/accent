package com.whizit.accent.word;

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
@RequestMapping("/api/word")
public class WordController {

	private final WordRepository wordRepository;

	public WordController(WordRepository wordRepository) {
		this.wordRepository = wordRepository;
	}

	@GetMapping("/words")
	List<Word> getAll() {
		return wordRepository.findAll();
	}

	@PostMapping("/words")
	Word newWord(@RequestBody Word newWord) {
		return wordRepository.save(newWord);
	}

	@GetMapping("/words/{id}")
	Optional<Word> findWordById(@PathVariable String id) {
		Optional<Word> word = wordRepository.findById(id);
		if (!word.isPresent()) {
			throw new WordNotFoundException(id);
		}
		return word;
	}

	@PutMapping("/words/{id}")
	Word replaceWord(@RequestBody Word newWord, @PathVariable(name = "id", required = true) String id) {
		return wordRepository.findById(id).map(word -> {
			word.setWordId(newWord.getId());
			word.setWordCategoryId(newWord.getWordCategoryId());
			word.setWord(newWord.getWord());
			word.setPronunciationAudioUrl(newWord.getPronunciationAudioUrl());
			word.setSpeakingIllustrationUrl(newWord.getSpeakingIllustrationUrl());
			word.setSpeakingSketchIllustrationUrl(newWord.getSpeakingSketchIllustrationUrl());
			word.setWordCategory(newWord.getWordCategory());
			word.setWodNotation(newWord.getWodNotation());
			return wordRepository.save(word);

		}).orElseGet(() -> {
			newWord.setId(id);
			return wordRepository.save(newWord);
		});

	}

	@DeleteMapping("/words/{id}")
	String deleteWord(@PathVariable String id) {
		wordRepository.deleteById(id);
		return "Word deleted successfully!!";
	}

}