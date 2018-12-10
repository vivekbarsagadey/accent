package com.whizit.accent.word;

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
@RequestMapping("/api/words")
public class WordController {

	private final WordRepository wordRepository;

	public WordController(WordRepository wordRepository) {
		this.wordRepository = wordRepository;
	}

	@GetMapping("/")
	ResponseEntity<List<Word>> getAll() {
		return new ResponseEntity<List<Word>>(wordRepository.findAll(), HttpStatus.OK);
	}

	@PostMapping("/")
	ResponseEntity<Word> newWord(@RequestBody Word newWord) {
		return new ResponseEntity<Word>(wordRepository.save(newWord), HttpStatus.OK);

	}

	@GetMapping("/{id}")
	ResponseEntity<Word> findWordById(@PathVariable String id) {
		final var response = new ResponseEntity<Word>(HttpStatus.OK);
		wordRepository.findById(id).ifPresentOrElse(u -> {

		}, () -> {
			response.status(HttpStatus.NOT_FOUND);
		});
		return response;
	}

	@PutMapping("/{id}")
	ResponseEntity<Word> replaceWord(@RequestBody Word newWord, @PathVariable(name = "id", required = true) String id) {
		return wordRepository.findById(id).map(word -> {
			word.setWordId(newWord.getId());
			word.setWordCategoryId(newWord.getWordCategoryId());
			word.setWord(newWord.getWord());
			word.setPronunciationAudioUrl(newWord.getPronunciationAudioUrl());
			word.setSpeakingIllustrationUrl(newWord.getSpeakingIllustrationUrl());
			word.setSpeakingSketchIllustrationUrl(newWord.getSpeakingSketchIllustrationUrl());
			word.setWordCategory(newWord.getWordCategory());
			word.setWodNotation(newWord.getWodNotation());
			return new ResponseEntity<Word>(wordRepository.save(word), HttpStatus.OK);

		}).orElseGet(() -> {
			newWord.setId(id);
			return new ResponseEntity<Word>(wordRepository.save(newWord), HttpStatus.OK);
		});

	}

	@DeleteMapping("/{id}")
	ResponseEntity<String> deleteWord(@PathVariable String id) {
		wordRepository.deleteById(id);
		return new ResponseEntity<String>("Word deleted successfully!!", HttpStatus.OK);
	}

}