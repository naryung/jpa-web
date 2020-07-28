package com.naryung.web.modules.store.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naryung.web.modules.store.entity.BookEntity;
import com.naryung.web.modules.store.entity.PublisherEntity;
import com.naryung.web.modules.store.model.Book;
import com.naryung.web.modules.store.repository.BookRepository;
import com.naryung.web.modules.store.repository.PublisherRepository;

@Service
public class BookService {
	
	@Autowired BookRepository bookRepository;
	@Autowired PublisherRepository publisherRepository;
	
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
	
	public List<Book> getByPbId(Long pbId) {
		PublisherEntity pe = publisherRepository.findOne(pbId);
		if(pe == null) {
			return new ArrayList<>();
		}
		
		return bookRepository.findByPublisher(pe)
				.stream()
				.map(BookEntity::toBookDto)
				.collect(Collectors.toList());
	}
	
	public List<Book> getByTitle(String title) {
		return bookRepository.findByTitle(title)
				.stream()
				.map(BookEntity::toBookDto)
				.collect(Collectors.toList());
	}
	
	public List<Book> getByAuthor(String author) {
		return bookRepository.findByAuthorNative(author)
				.stream()
				.map(BookEntity::toBookDto)
				.collect(Collectors.toList());
	}
	
	@Transactional
	public Book createBook(Book book) {
		BookEntity entity = new BookEntity();
		
		entity.setTitle(book.getTitle());
		entity.setAuthor(book.getAuthor());
		
		if(book.getPbId() != null && book.getPbId() > 0) {
			entity.setPublisher(publisherRepository.findOne(book.getPbId()));
		}
		
		return bookRepository.save(entity).toBookDto();
	}
	
	@Transactional
	public Book updateBook(Long id, Book book) {
		BookEntity entity = bookRepository.findOne(id);
		
		entity.setTitle(book.getTitle());
		entity.setAuthor(book.getAuthor());
		
		if(book.getPbId() != null && book.getPbId() > 0) {
			entity.setPublisher(publisherRepository.findOne(book.getPbId()));
		}
		
		return entity.toBookDto();
	}
	
	@Transactional
	public boolean deleteBook(Long id) {
		bookRepository.delete(id);
		return true;
	}
}
