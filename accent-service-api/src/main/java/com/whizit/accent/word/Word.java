package com.whizit.accent.word;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "TB_WORD")
@Data
@Access(AccessType.PROPERTY)
public class Word {

	@Column(name = "word_id", length = 100, nullable = false, unique = true)
	private String wordId;
	private String wordCategoryId;
	private String word;
	private String pronunciationAudioUrl;
	private String speakingIllustrationUrl;
	private String speakingSketchIllustrationUrl;
	private String wordCategory;
	private String wodNotation;

}
