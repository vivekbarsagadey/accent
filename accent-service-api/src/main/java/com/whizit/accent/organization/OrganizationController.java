package com.whizit.accent.organization;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/organization")
public class OrganizationController {

	private final OrganizationRepository organizationRepository;

	public OrganizationController(OrganizationRepository organizationRepository) {
		this.organizationRepository = organizationRepository;
	}

	@GetMapping("/organizations")
	List<Organization> getAll() {
		return organizationRepository.findAll();
	}

	@PostMapping("/organizations")
	Organization newOrganization(@RequestBody Organization newOrganization) {
		return organizationRepository.save(newOrganization);
	}

	@GetMapping("/organizations/{id}")
	Optional<Organization> findOrganizationById(@PathVariable String id) {
		Optional<Organization> organization = organizationRepository.findById(id);
		if (!organization.isPresent()) {
			throw new OrganizationNotFoundException(id);
		}
		return organization;
	}

	@PutMapping("/organizations/{id}")
	Organization replaceOrganization(@RequestBody Organization newOrganization,
			@PathVariable(name = "id", required = true) String id) {
		return organizationRepository.findById(id).map(organization -> {
			organization.setId(newOrganization.getId());
			organization.setOrgId(newOrganization.getOrgId());
			organization.setOrgName(newOrganization.getOrgName());
			organization.setOrgContactDetails(newOrganization.getOrgContactDetails());
			return organizationRepository.save(organization);
		}).orElseGet(() -> {
			newOrganization.setId(id);
			return organizationRepository.save(newOrganization);
		});

	}

	@DeleteMapping("/organizations/{id}")
	String deleteOrganization(@PathVariable String id) {
		organizationRepository.deleteById(id);
		return "Organization deleted successfully!!";
	}

}