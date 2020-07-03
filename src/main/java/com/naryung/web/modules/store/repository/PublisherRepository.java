package com.naryung.web.modules.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naryung.web.modules.store.entity.PublisherEntity;

public interface PublisherRepository extends JpaRepository<PublisherEntity, Long> {

}
