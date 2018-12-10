package com.whizit.accent.recommendersystemresponse;

import java.util.List;

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

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/systemresponses")
public class RecommenderSystemResponseController {

	private final RecommenderSystemResponseRepository systemResponseRepo;

	public RecommenderSystemResponseController(RecommenderSystemResponseRepository systemResponseRepo) {
		this.systemResponseRepo = systemResponseRepo;
	}

	@GetMapping("/")
	@ApiOperation(value = "Get All", produces = "application/text")
	public ResponseEntity<List<RecommenderSystemResponse>> findAll() {
		return new ResponseEntity<List<RecommenderSystemResponse>>(systemResponseRepo.findAll(), HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<RecommenderSystemResponse> save(@RequestBody RecommenderSystemResponse newResponse) {
		return new ResponseEntity<RecommenderSystemResponse>((systemResponseRepo.save(newResponse)), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<RecommenderSystemResponse> findById(@PathVariable String id) {
		final var response = new ResponseEntity<RecommenderSystemResponse>(HttpStatus.OK);
		systemResponseRepo.findById(id).ifPresentOrElse(u -> {
			response.ok(u);
		}, () -> {
			response.status(HttpStatus.NOT_FOUND);
		});
		return response;
	}

	@PutMapping("/{id}")
	public ResponseEntity<RecommenderSystemResponse> update(@RequestBody RecommenderSystemResponse newResponse,
			@PathVariable(name = "id", required = true) String id) {
		return systemResponseRepo.findById(id).map(res -> {
			res.setRecommenderId(newResponse.getRecommenderId());
			res.setRecommenderInputJson(newResponse.getRecommenderInputJson());
			res.setRecommenderOutputJson(newResponse.getRecommenderOutputJson());
			return new ResponseEntity<RecommenderSystemResponse>(systemResponseRepo.save(res), HttpStatus.OK);
		}).orElseGet(() -> {
			return new ResponseEntity<RecommenderSystemResponse>(newResponse, HttpStatus.NOT_FOUND);
		});

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable String id) {
		systemResponseRepo.deleteById(id);
		return new ResponseEntity<String>("deleted successfully!!", HttpStatus.OK);
	}

}