package com.whizit.accent.organization;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "TB_ORGANIZATION")
@Data
@Access(AccessType.PROPERTY)
public class Organization {

	@Column(name = "org_id", length = 100, nullable = false, unique = true)
	private String orgId;
	private String orgName;
	private String orgContactDetails;

}
