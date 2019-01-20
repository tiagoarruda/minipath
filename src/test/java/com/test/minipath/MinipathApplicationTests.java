package com.test.minipath;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.minipath.controller.URLController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MinipathApplicationTests {
	
	@Autowired
	private URLController controller;

	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
	}

}

