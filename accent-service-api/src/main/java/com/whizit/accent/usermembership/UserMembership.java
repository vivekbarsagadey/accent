package com.whizit.accent.usermembership;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.whizit.accent.common.domain.BaseEntity;

import lombok.Data;

@Entity
@Table(name = "TB_USER_MEMBERSHIP")
@Data
@Access(AccessType.PROPERTY)
public class UserMembership extends BaseEntity{

	@Column(name = "user_membership_id", length = 100, nullable = false, unique = true)
	private String userMembershipId;
	private String membershipId;
	private String userId;
	private String userMembershipStartDate;
	private String userMembershipEndDate;
	private String amountPaid;
	private String paymentDate;
	private String paymentMode;
	private String userMembershipStatus;

}
