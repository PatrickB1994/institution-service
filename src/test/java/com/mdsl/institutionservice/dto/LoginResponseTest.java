package com.mdsl.institutionservice.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginResponseTest {

	@Test
	public void testGettersAndSetters() {
		// Arrange
		LoginResponse loginResponse = new LoginResponse();
		String token = "testToken";
		String refreshToken = "testRefreshToken";

		// Act
		loginResponse.setToken(token);
		loginResponse.setRefreshToken(refreshToken);

		// Assert
		assertEquals(token, loginResponse.getToken());
		assertEquals(refreshToken, loginResponse.getRefreshToken());
	}

	@Test
	public void testDefaultConstructor() {
		// Arrange & Act
		LoginResponse loginResponse = new LoginResponse();

		// Assert
		assertNull(loginResponse.getToken());
		assertNull(loginResponse.getRefreshToken());
	}

	@Test
	public void testChainableSetters() {
		// Arrange
		String token = "testToken";
		String refreshToken = "testRefreshToken";

		// Act
		LoginResponse loginResponse = new LoginResponse()
				.setToken(token)
				.setRefreshToken(refreshToken);

		// Assert
		assertEquals(token, loginResponse.getToken());
		assertEquals(refreshToken, loginResponse.getRefreshToken());
	}

	@Test
	public void testAllArgsConstructor() {
		// Arrange
		String token = "testToken";
		String refreshToken = "testRefreshToken";

		// Act
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setToken(token);
		loginResponse.setRefreshToken(refreshToken);

		// Assert
		assertEquals(token, loginResponse.getToken());
		assertEquals(refreshToken, loginResponse.getRefreshToken());
	}
}
