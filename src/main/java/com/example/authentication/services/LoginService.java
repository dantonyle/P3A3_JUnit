package com.example.authentication.services;

import com.example.authentication.models.User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.annotation.SessionScope;


@Service
@SessionScope
public class LoginService {

    public boolean userNotNull(User newUser) {
        if (newUser == null) return false;
        
        return true;
    }

    public boolean AuthenticateUser(User user, String enteredPassword){
        return (user.getPassword().equals(enteredPassword));
    }
   
    public boolean userEmailCheck(User user, String email) {
    	if (user.getEmail().equals(email)) {
    		return true;
    	}
    	
    	return false;
    }
    
    public boolean emptyOrBlankCheck(User user) { 
    	
    	if (StringUtils.containsWhitespace(user.getName()) 
    			|| StringUtils.containsWhitespace(user.getEmail()) 
    			|| StringUtils.containsWhitespace(user.getPassword()) 
    			|| !StringUtils.hasText(user.getName()) 
    			|| !StringUtils.hasText(user.getEmail()) 
    			|| !StringUtils.hasText(user.getPassword()) ) {
    		return true;
    	}
    	return false;
    }
    
 

}
