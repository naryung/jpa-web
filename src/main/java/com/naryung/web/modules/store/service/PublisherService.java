package com.naryung.web.modules.store.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naryung.web.modules.store.entity.PublisherEntity;
import com.naryung.web.modules.store.model.Publisher;
import com.naryung.web.modules.store.repository.PublisherRepository;

@Service
public class PublisherService {
	
	@Autowired PublisherRepository publisherRepository;
	
	@Transactional
	public List<Publisher> getAll(Pageable pageable) {
		Page<PublisherEntity> publisherPage = publisherRepository.findAll(pageable);
		
		return publisherPage.getContent()
				.stream()
				.map(PublisherEntity::toPublisherDto)
				.collect(Collectors.toList());
	}
	
	@Transactional
	public Publisher createPublisher(Publisher p) {
		PublisherEntity entity = new PublisherEntity();
		entity.setName(p.getName());
		
		return publisherRepository.save(entity).toPublisherDto();
	}
	
	@Transactional
	public PublisherEntity getOne(Long id) {
		return publisherRepository.findOne(id);
	}
	
}