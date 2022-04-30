package com.email.springEmail.registration.token;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TokenService {
	@Autowired
	TokenRepository tokenRepository;
	 
	public Optional<Token> getToken(String token) {
        return tokenRepository.findByToken(token);
    }
	
	

	  public int setConfirmedAt(String token) {
	        return tokenRepository.updateConfirmedAt(
	                token, LocalDateTime.now());
	    }
	
}
