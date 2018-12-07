package com.whizit.accent.user;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.whizit.accent.common.domain.BaseEntity;

import lombok.Data;

@Entity
@Table(name = "TB_USER")
@Data
@Access(AccessType.PROPERTY)
public class User extends BaseEntity{

	@Column(name = "user_id", length = 100, nullable = false, unique = true)
	private String userId;
	private String login;
	private String password;
	private String firstName;
	private String lastName;
	private String emailId;
	private String phone;
	private String role;

}
