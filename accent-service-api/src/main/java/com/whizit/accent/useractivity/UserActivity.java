package com.whizit.accent.useractivity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.whizit.accent.common.domain.BaseEntity;

import lombok.Data;

@Entity
@Table(name = "TB_USER_ACTIVITY")
@Data
@Access(AccessType.PROPERTY)
public class UserActivity extends BaseEntity{

	@Column(name = "user_activity_id", length = 100, nullable = false, unique = true)
	private String useractivityId;
	private String userId;
	private String userActivityTime;
	private String userActivityType;

}
