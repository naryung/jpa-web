package com.naryung.web.modules.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naryung.web.modules.store.entity.PublisherEntity;
import com.naryung.web.modules.store.model.Publisher;
import com.naryung.web.modules.store.service.PublisherService;

@RestController
@RequestMapping("/publishers")
public class PublisherController {
	
	@Autowired PublisherService publisherService;
	
	@GetMapping("")
	public List<Publisher> getPublishers(Pageable pageable) {
		return publisherService.getAll(pageable);
	}
	
	@PostMapping("")
	public Publisher createPublisher(@RequestBody Publisher p) {
		return publisherService.createPublisher(p);
	}
	
	@GetMapping("/{id}")
	public Publisher getPublisher(@PathVariable Long id) {
		return publisherService.getOne(id).toPublisherDto();
	}
	
}