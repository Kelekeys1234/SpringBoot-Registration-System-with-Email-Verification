package com.email.springEmail.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.email.springEmail.appuser.User;
import com.email.springEmail.appuser.UserService;

@RestController
@RequestMapping("api/registration")
public class RegisterController {
	@Autowired
	RegistrationService service;
	@Autowired
	UserService userService;
	
	@PostMapping("/registers")
	public String register(@RequestBody Registration register) throws Exception {
		return service.registration(register);
		
	}
	 @GetMapping("/confirm") 
	 public String confirm(@RequestParam("token")
	 String token) {
		 return service.confirmToken(token); 
		 }
	
	 @PostMapping("{id}")
	 public String updateUser(@PathVariable("id") Long id , @RequestBody User user ) throws Exception {
		userService.updateUser(id, user);
		 
		return "worked";
		 
	 }

}
