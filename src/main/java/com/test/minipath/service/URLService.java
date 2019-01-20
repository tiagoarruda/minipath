package com.test.minipath.service;

import com.test.minipath.entity.URL;

public interface URLService {

	URL createURL(URL url);
	
	URL findById(Long id);

	URL findByPath(String path);

	String encodeID(Long id);

	Long decodeID(String id);
	
	String decodeURL(String id);

}