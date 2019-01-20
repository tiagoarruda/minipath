package com.test.minipath.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.minipath.entity.URL;
import com.test.minipath.exception.ResourceNotFoundException;
import com.test.minipath.jpa.URLRepository;

@Service
public class URLServiceImpl implements URLService {
	
	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int    BASE     = ALPHABET.length();

	@Autowired
	private URLRepository repo;
	
	/* (non-Javadoc)
	 * @see com.test.minipath.service.URLService#createURL(com.test.minipath.entity.URL)
	 */
	@Override
	public URL createURL(URL url) {
		URL path = repo.findByPath(url.getPath());
		if (path != null ) {
			return path;
		}
		
		return repo.save(url);
	}
	
	/* (non-Javadoc)
	 * @see com.test.minipath.service.URLService#findById(java.lang.Long)
	 */
	@Override
	public URL findById(Long id) {
		Optional<URL> result = repo.findById(id);
		if (!result.isPresent())
			throw new ResourceNotFoundException("URL", "ID", id);
		
		return result.get();
	}
	
	/* (non-Javadoc)
	 * @see com.test.minipath.service.URLService#findByPath(java.lang.String)
	 */
	@Override
	public URL findByPath(String path) {
		URL result = repo.findByPath(path);
		if (result == null)
			throw new ResourceNotFoundException("URL", "path", path);
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.test.minipath.service.URLService#encodeID(java.lang.Long)
	 * REFERENCE:
	 * https://stackoverflow.com/questions/742013/how-do-i-create-a-url-shortener
	 */
	@Override
	public String encodeID(Long id) {
		StringBuilder sb = new StringBuilder();
        while ( id > 0 ) {
        	Long position = id % BASE;
            sb.append( ALPHABET.charAt( position.intValue() ) );
            id /= BASE;
        }
        return sb.reverse().toString(); 
	}
	
	/* (non-Javadoc)
	 * @see com.test.minipath.service.URLService#decodeID(java.lang.String)
	 * REFERENCE:
	 * https://stackoverflow.com/questions/742013/how-do-i-create-a-url-shortener
	 */
	@Override
	public Long decodeID(String encodedId) {
		Long num = 0L;
        for ( int i = 0; i < encodedId.length(); i++ )
            num = num * BASE + ALPHABET.indexOf(encodedId.charAt(i));
        return num;
	}
	
	/* (non-Javadoc)
	 * @see com.test.minipath.service.URLService#decodeURL(java.lang.String)
	 */
	@Override
	public String decodeURL(String id) {
		Long entityId = decodeID(id);
		URL url = repo.findById(entityId).get();
		return url.getPath();
	}
}
