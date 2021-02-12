package com.example.authentication;

import com.example.authentication.controllers.LoginController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
public class AuthenticationWebTests {

    @Autowired
    private LoginController controller;
    
    @Test
	public void viewTest() {
		
		controller = new LoginController();
		
		assertEquals(controller.showGreeting(null),"greeting");
		
		assertEquals(controller.showLogin(null),"login");
		
		assertEquals(controller.submitLogin("user", "password"), "Success");
		
	}


}
