package com.naryung.web.modules.store.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Publisher {

	private Long id;
	private String name;
	private List<Book> books;
	
}
