package com.tonyb650.authentication.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.tonyb650.authentication.models.LoginUser;
import com.tonyb650.authentication.models.User;
import com.tonyb650.authentication.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public User register(User newUser, BindingResult result) {
		// search DB for proposed email address
		Optional<User> possibleUser = userRepository.findByEmail(newUser.getEmail()); 
		if(possibleUser.isPresent()) {
			//reject email: already in use
			result.rejectValue("email", "Matches", "Email is already in use!");
		}
		if(!newUser.getPassword().equals(newUser.getConfirm())) {
			//reject passwords don't match
			result.rejectValue("confirm", "Matches", "Passwords must match!");
		}
//		System.out.println(result);
		if(!result.hasErrors()) {
	        // Hash and set password, save user to database
			String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
			newUser.setPassword(hashed);
			userRepository.save(newUser);
		}
		return null;
	}
	
	public User login(LoginUser newLoginObject, BindingResult result) {
    	// Find user in the DB by email (if present)
		Optional<User> possibleUser = userRepository.findByEmail(newLoginObject.getEmail()); 
		if(!possibleUser.isPresent()) {
			//reject because email doesn't exist
			result.rejectValue("email", "Matches", "Incorrect email!");
			return null;
		}
		// Optional user is present, so 'get'
		User user = possibleUser.get();
		
        // Reject if BCrypt password match fails
		if(!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
		    result.rejectValue("password", "Matches", "Invalid Password!");
		}
		if(result.hasErrors()) {
	        // Return null if result has errors
			return null;
		}
        // Otherwise, return the user object
		return user;
	}
}
