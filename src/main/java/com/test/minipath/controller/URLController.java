package com.test.minipath.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.test.minipath.entity.URL;
import com.test.minipath.service.URLService;

@RestController
public class URLController {
	
	@Autowired
	private URLService service;
	
	@PostMapping(value="/url", produces = "application/json", consumes = "application/json")
	@ResponseBody
	@ResponseStatus(value = HttpStatus.CREATED)
	public String createURL(@RequestBody URL url) {
		url = service.createURL(url);
		
		//TODO: Create a property containing the address bellow
		return "http://localhost:8080/go/" + service.encodeID(url.getId());
	}
	
	@GetMapping(value = "/url/{id}", produces = "application/json",consumes = "application/json")
	@ResponseBody
	public URL getURL(@PathVariable("id") Long id) {
		return service.findById(id);
	}
	
	@GetMapping(value="/go/{id}")
	public ResponseEntity redirect(@PathVariable("id") String id) {
		return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, service.decodeURL(id)).build();
	}
}
