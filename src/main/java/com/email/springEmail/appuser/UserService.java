package com.email.springEmail.appuser;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import javax.imageio.spi.RegisterableService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.email.springEmail.emailSending.EmailSending;
import com.email.springEmail.registration.Registration;
import com.email.springEmail.registration.RegistrationService;
import com.email.springEmail.registration.token.Token;
import com.email.springEmail.registration.token.TokenRepository;
@Service
public class UserService implements UserDetailsService{
	@Autowired
	UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder  bCryptPasswordEncoder;
	@Autowired
	TokenRepository tokenRepo;
	@Autowired
	EmailSending emailSending ;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return  userRepository.findByEmail(email).orElseThrow(() ->
        new UsernameNotFoundException("user not found "));
	}
	 public String signIn(User register) throws Exception {
		 Boolean exists = userRepository.findByEmail(register.getEmail()).isPresent();
		
		 if(exists) {
			 boolean existCheck = userRepository.findByEmailAndEnable(register.getEmail(), false).isPresent();
			 if(existCheck ) {
			User user = userRepository.findByEmail(register.getEmail()).get();
				 String token = UUID.randomUUID().toString();
				 
				 Token confirmToken = new Token(
						 token,
						 LocalDateTime.now(),
						 LocalDateTime.now().plusMinutes(15),
						 user
						 
						 );
				 
				  tokenRepo.save(confirmToken);
				return token;
				 
			 }
			 
			 throw new Error("email taking");
			 
	 }
		 
		 String encodedPassword = bCryptPasswordEncoder.encode(register.getPassword());
		 register.setPassword(encodedPassword);
		 userRepository.save(register);
		 
		 String token = UUID.randomUUID().toString();
		 
		 Token confirmToken = new Token(
				 token,
				 LocalDateTime.now(),
				 LocalDateTime.now().plusMinutes(15),
				 register
				 
				 );
		 tokenRepo.save(confirmToken);
		return token;
}
	  public int enableUser(String email) { 
		  return  userRepository.enableUser(email); 
	  }
	  
	  
	public String updateUser(Long id , User user ) throws Exception {
		 Boolean exists = userRepository.findByEmail(user.getEmail()).isPresent();
		 if(!exists) {
			throw new Exception("email does not exist. please create an account") ;
		 }
		User getId = userRepository.findById(id).get();
		
		
		 String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		 getId.setPassword(encodedPassword);
		userRepository.save(getId);
		
		
		
		return "Password Updated";
		
	}
	 

}
