package com.whizit.accent.userpracticesession;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "TB_USER_PRACTICE_SESSION")
@Data
@Access(AccessType.PROPERTY)
public class UserPracticeSession {

	@Column(name = "user_practice_session_id", length = 100, nullable = false, unique = true)
	private String userPracticeSessionId;
	private String userId;
	private String wordId;
	private String score;
	private String audioRecordingUrl;
	private String sessionStartTime;
	private String sessionEndTime;
}
