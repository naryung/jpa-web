package com.naryung.web.modules.store.entity;

import javax.persistence.*;

import com.naryung.web.modules.store.model.Book;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "BOOK")
public class BookEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "B_ID")
	private Long id;
	
	@Column(name = "B_TITLE")
	private String title;
	
	@Column(name = "AUTHOR")
	private String author;
	
	@Column(name = "PB_ID")
	private Long pbId;
	
	public Book toBookDto() {
		Book book = new Book();
		book.setId(this.id);
		book.setTitle(this.title);
		book.setAuthor(author);
		book.setPbId(this.pbId);
		
		return book;
	}
	
}
