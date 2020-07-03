package com.naryung.web.modules.store.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naryung.web.modules.store.entity.BookEntity;
import com.naryung.web.modules.store.model.Book;
import com.naryung.web.modules.store.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	BookRepository bookRepository;
	
	public List<Book> getAll(Pageable pageable) {
		Page<BookEntity> bookPage =  bookRepository.findAll(pageable);
		
		return bookPage.getContent()
    			.stream()
    			.map(BookEntity::toBookDto)
    			.collect(Collectors.toList());
	}
		
	public Book getOne(Long id) {
		return bookRepository.findOne(id).toBookDto();
	}
	
	@Transactional
	public Book createBook(Book book) {
		BookEntity entity = new BookEntity();
		entity.setTitle(book.getTitle());
		entity.setAuthor(book.getAuthor());
		entity.setPbId(book.getPbId());
		
		return bookRepository.save(entity).toBookDto();
	}
	
	@Transactional
	public Book updateBook(Long id, Book book) {
		BookEntity entity = bookRepository.findOne(id);
		entity.setTitle(book.getTitle());
		entity.setAuthor(book.getAuthor());
		entity.setPbId(book.getPbId());
		
		return entity.toBookDto();	
	}
	
	@Transactional
	public boolean deleteBook(Long id) {
		bookRepository.delete(id);
		return true;
	}
}
