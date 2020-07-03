package com.naryung.web.modules.store.entity;

import javax.persistence.*;

import com.naryung.web.modules.store.model.Publisher;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PUBLISHER")
public class PublisherEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PB_ID")
	private Long id;
	
	@Column(name = "PB_NAME")
	private String name;
	
	public Publisher toPublisherDto() {
		Publisher publisher = new Publisher();
		publisher.setId(this.id);
		publisher.setName(this.name);
		
		return publisher;
	}
}
