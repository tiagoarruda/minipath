package com.test.minipath.jpa;

import org.springframework.data.repository.CrudRepository;

import com.test.minipath.entity.URL;

public interface URLRepository extends CrudRepository<URL, Long> {
	
	URL findByPath(String path);
}
