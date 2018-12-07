package com.whizit.accent.recommendersystemresponse;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.whizit.accent.common.domain.BaseEntity;

import lombok.Data;

@Entity
@Table(name = "TB_RECOMMENDER_SYSTEM_RESPONSE")
@Data
@Access(AccessType.PROPERTY)
public class RecommenderSystemResponse extends BaseEntity{

	@Column(name = "recommender_id", length = 100, nullable = false, unique = true)
	private String recommenderId;
	private String recommenderInputJson;
	private String recommenderOutputJson;
}
