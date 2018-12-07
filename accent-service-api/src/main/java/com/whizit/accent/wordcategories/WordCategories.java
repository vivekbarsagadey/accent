package com.whizit.accent.wordcategories;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "TB_WORD_CATEGORIES")
@Data
@Access(AccessType.PROPERTY)
public class WordCategories {

	@Column(name = "word_category_id", length = 100, nullable = false, unique = true)
	private String wordCategoryId;
	private String wordCategoryName;

}
