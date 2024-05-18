package com.mdsl.institutionservice.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RefreshTokenRequestTest {

	@Test
	public void testGettersAndSetters() {
		// Arrange
		RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
		String refreshToken = "testRefreshToken";

		// Act
		refreshTokenRequest.setRefreshToken(refreshToken);

		// Assert
		assertEquals(refreshToken, refreshTokenRequest.getRefreshToken());
	}

	@Test
	public void testDefaultConstructor() {
		// Arrange & Act
		RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();

		// Assert
		assertNull(refreshTokenRequest.getRefreshToken());
	}

	@Test
	public void testAllArgsConstructor() {
		// Arrange
		String refreshToken = "testRefreshToken";

		// Act
		RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
		refreshTokenRequest.setRefreshToken(refreshToken);

		// Assert
		assertEquals(refreshToken, refreshTokenRequest.getRefreshToken());
	}
}
