package com.whizit.accent.organization;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/organizations")
public class OrganizationController {

	private final OrganizationRepository organizationRepository;

	public OrganizationController(OrganizationRepository organizationRepository) {
		this.organizationRepository = organizationRepository;
	}

	@GetMapping("/")
	ResponseEntity<List<Organization>> getAll() {
		return new ResponseEntity<List<Organization>>(organizationRepository.findAll(), HttpStatus.OK);
	}

	@PostMapping("/")
	ResponseEntity<Organization> newOrganization(@RequestBody Organization newOrganization) {
		return new ResponseEntity<Organization>(organizationRepository.save(newOrganization), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	ResponseEntity<Organization> findOrganizationById(@PathVariable String id) {
		final var response = new ResponseEntity<Organization>(HttpStatus.OK);
		organizationRepository.findById(id).ifPresentOrElse(u -> {

		}, () -> {
			response.status(HttpStatus.NOT_FOUND);
		});
		return response;
	}

	@PutMapping("/{id}")
	ResponseEntity<Organization> replaceOrganization(@RequestBody Organization newOrganization,
			@PathVariable(name = "id", required = true) String id) {
		return organizationRepository.findById(id).map(organization -> {
			organization.setId(newOrganization.getId());
			organization.setOrgId(newOrganization.getOrgId());
			organization.setOrgName(newOrganization.getOrgName());
			organization.setOrgContactDetails(newOrganization.getOrgContactDetails());
			return new ResponseEntity<Organization>(organizationRepository.save(organization), HttpStatus.OK);
		}).orElseGet(() -> {
			newOrganization.setId(id);
			return new ResponseEntity<Organization>(organizationRepository.save(newOrganization), HttpStatus.NOT_FOUND);
		});

	}

	@DeleteMapping("/{id}")
	ResponseEntity<String> deleteOrganization(@PathVariable String id) {
		organizationRepository.deleteById(id);
		return new ResponseEntity<String>("Organization deleted successfully!!", HttpStatus.OK);
	}

}