package com.example.authentication;


import com.example.authentication.models.User;
import com.example.authentication.repositories.UserRepository;
import com.example.authentication.services.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


/*import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.annotation.SessionScope;
import org.junit.jupiter.api.Assertions.*;*/
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;


@DataJpaTest
public class AuthenticationTests {

	@Autowired
	private UserRepository userRepository;

	private LoginService loginService;
	
	User dummyUser;

	@BeforeEach
	public void setUp() {
		loginService = new LoginService();
		
		dummyUser = new User();
		dummyUser.setName("Dummy");
		dummyUser.setEmail("test@test.com");
		dummyUser.setPassword("password");
		userRepository.save(dummyUser);
	}
	
	@Test
	public void AddToDatabaseCheck() {

		User noUser = null;
		
		assertFalse(loginService.userNotNull(noUser));
		
		assertTrue(loginService.userNotNull(dummyUser));
		
		System.out.println("dummyUser created:" + dummyUser.getName());
	
		// when
		User found = userRepository.findByName(dummyUser.getName());
		System.out.println("AddToDatabaseCheck found username:" + found.getName());
		// then

		assertEquals(found.getName(), "Dummy");
	}

	@Test
	public void AddAndCheckUserPassword() {

		
		System.out.println("dummyUser2 created:" + dummyUser.getName());
		
		boolean authenticated = loginService.AuthenticateUser(dummyUser, "password");

		assertTrue(authenticated);
		System.out.println("authenticated equals: " + authenticated);
		
	}
	
	@Test 
	public void emailStringCheck() {
	
		User foundUser = userRepository.findByName("Dummy");
		
		assertTrue(loginService.userEmailCheck(foundUser, "test@test.com"));
		assertFalse(loginService.userEmailCheck(foundUser, "notCorrect"));
		
		assertEquals("test@test.com", foundUser.getEmail());
		
	}
	
	@Test
	public void checkForBlankOrEmpty() {
		
		assertFalse(loginService.emptyOrBlankCheck(dummyUser));
		
		dummyUser.setName(" ");
		assertTrue(loginService.emptyOrBlankCheck(dummyUser));
		
		dummyUser.setName("DummyAdvanced");
		dummyUser.setPassword(null);
		assertTrue(loginService.emptyOrBlankCheck(dummyUser));
		
	}
	

}
