package com.naryung.web.modules.store.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.naryung.web.modules.store.entity.BookEntity;
import com.naryung.web.modules.store.entity.PublisherEntity;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
	
	List<BookEntity> findByPublisher(@Param("publisher") PublisherEntity publisher);
	
	// JPA 직접 정의 - 이름기반 파라미터
	@Query("SELECT b FROM BookEntity b WHERE b.title LIKE CONCAT('%', :title, '%')")
	List<BookEntity> findByTitle(@Param("title") String title);
	
	// JPA 직접 정의 - 위치기반 파라미터
	@Query("SELECT b FROM BookEntity b WHERE b.author LIKE '%' || ?1 || '%'")
	List<BookEntity> findByAuthor(String author);
	
	// Spring Data JPA Native Query 사용
	@Query(value = "SELECT * FROM BOOK WHERE AUTHOR LIKE '%' || ?1 || '%'", nativeQuery = true)
	List<BookEntity> findByAuthorNative(String author);
}