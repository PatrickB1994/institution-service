package com.mdsl.institutionservice.entity;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RefreshTokenEntityTest
{

	@Test
	public void testGetterAndSetterMethods()
	{
		// Arrange
		Long id = 1L;
		String token = "testToken";
		Instant expiryDate = Instant.now();
		UserEntity user = new UserEntity();

		RefreshTokenEntity refreshToken = new RefreshTokenEntity();

		// Act
		refreshToken.setId(id);
		refreshToken.setToken(token);
		refreshToken.setExpiryDate(expiryDate);
		refreshToken.setUser(user);

		// Assert
		assertEquals(id, refreshToken.getId());
		assertEquals(token, refreshToken.getToken());
		assertEquals(expiryDate, refreshToken.getExpiryDate());
		assertEquals(user, refreshToken.getUser());
	}

	@Test
	public void testBuilder()
	{
		// Arrange
		Long id = 1L;
		String token = "testToken";
		Instant expiryDate = Instant.now();
		UserEntity user = new UserEntity();

		// Act
		RefreshTokenEntity refreshToken = RefreshTokenEntity.builder().id(id).token(token).expiryDate(expiryDate).user(user).build();

		// Assert
		assertNotNull(refreshToken);
		assertEquals(id, refreshToken.getId());
		assertEquals(token, refreshToken.getToken());
		assertEquals(expiryDate, refreshToken.getExpiryDate());
		assertEquals(user, refreshToken.getUser());
	}

	@Test
	public void testAllArgsConstructor()
	{
		// Arrange
		Long id = 1L;
		String token = "testToken";
		Instant expiryDate = Instant.now();
		UserEntity user = new UserEntity();

		// Act
		RefreshTokenEntity refreshToken = new RefreshTokenEntity(id, token, expiryDate, user);

		// Assert
		assertNotNull(refreshToken);
		assertEquals(id, refreshToken.getId());
		assertEquals(token, refreshToken.getToken());
		assertEquals(expiryDate, refreshToken.getExpiryDate());
		assertEquals(user, refreshToken.getUser());
	}
}
