package com.naryung.web.modules.store.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Book {

	private Long id;
	private String title;
	private String author;
	private Long pbId;
	private String pbName;
	
}
