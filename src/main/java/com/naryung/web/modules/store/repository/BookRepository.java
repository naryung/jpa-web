package com.naryung.web.modules.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naryung.web.modules.store.entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
	
}