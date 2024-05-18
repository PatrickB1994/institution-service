package com.mdsl.institutionservice.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginRequestTest {

	@Test
	public void testGettersAndSetters() {
		// Arrange
		LoginRequest loginRequest = new LoginRequest();
		String userName = "testUser";
		String password = "testPassword";

		// Act
		loginRequest.setUserName(userName);
		loginRequest.setPassword(password);

		// Assert
		assertEquals(userName, loginRequest.getUserName());
		assertEquals(password, loginRequest.getPassword());
	}

	@Test
	public void testDefaultConstructor() {
		// Arrange & Act
		LoginRequest loginRequest = new LoginRequest();

		// Assert
		assertNull(loginRequest.getUserName());
		assertNull(loginRequest.getPassword());
	}

	@Test
	public void testAllArgsConstructor() {
		// Arrange
		String userName = "testUser";
		String password = "testPassword";

		// Act
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUserName(userName);
		loginRequest.setPassword(password);

		// Assert
		assertEquals(userName, loginRequest.getUserName());
		assertEquals(password, loginRequest.getPassword());
	}
}
