package com.test.minipath.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.minipath.entity.URL;
import com.test.minipath.jpa.URLRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class URLServiceTest {
	
	@Autowired
	private URLService service;
	
	@Mock
	private URLRepository repository;
	
	@Test
	public void testEncode() {
		Long testID = 1234L;
		String encoded = service.encodeID(testID);
		assertThat(encoded).isEqualTo("t4");
	}
	
	@Test
	public void testDecode() {
		String testID = "t4";
		Long decoded = service.decodeID(testID);
		assertThat(decoded).isEqualTo(1234L);
	}
	
	@Test
	public void testSucessfullInsertion() {
		URL testURL = getTestURL();
		given(this.repository.save(testURL)).willReturn(testURL);
		URL response = service.createURL(testURL);
		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo(1L);
	}
	
	private URL getTestURL() {
		URL url = new URL("test.com");
		url.setId(1L);
		url.setPath("test.com");
		
		return url;
	}

}
