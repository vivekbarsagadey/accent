package com.whizit.accent.membership;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "TB_MEMBERSHIP")
@Data
@Access(AccessType.PROPERTY)
public class Membership {

	@Column(name = "membership_id", length = 100, nullable = false, unique = true)
	private String id;
	private String name;
	private String type;
	private String period;
	private Double fee;
	private String details;
	private String discount;
	private String discountPeriod;

}