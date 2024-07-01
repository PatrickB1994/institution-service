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
		String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNzE5ODI5MjQyLCJleHAiOjE3MTk4Mjk0MjJ9.iwFnueJj3SYQ72-2M04UV0enenT3G1wDVH5boQIufSY1MQ2ghM1Ved_LSDK8YldU";
		Instant expiryDate = Instant.now();
		UserEntity user = new UserEntity();

		RefreshTokenEntity refreshToken = new RefreshTokenEntity();

		// Act
		refreshToken.setId(id);
		refreshToken.setToken(token);
		refreshToken.setUser(user);

		// Assert
		assertEquals(id, refreshToken.getId());
		assertEquals(token, refreshToken.getToken());
		assertEquals(user, refreshToken.getUser());
	}

	@Test
	public void testBuilder()
	{
		// Arrange
		Long id = 1L;
		String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNzE5ODI5MjQyLCJleHAiOjE3MTk4Mjk0MjJ9.iwFnueJj3SYQ72-2M04UV0enenT3G1wDVH5boQIufSY1MQ2ghM1Ved_LSDK8YldU";
		Instant expiryDate = Instant.now();
		UserEntity user = new UserEntity();

		// Act
		RefreshTokenEntity refreshToken = RefreshTokenEntity.builder().id(id).token(token).user(user).build();

		// Assert
		assertNotNull(refreshToken);
		assertEquals(id, refreshToken.getId());
		assertEquals(token, refreshToken.getToken());
		assertEquals(user, refreshToken.getUser());
	}

	@Test
	public void testAllArgsConstructor()
	{
		// Arrange
		Long id = 1L;
		String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNzE5ODI5MjQyLCJleHAiOjE3MTk4Mjk0MjJ9.iwFnueJj3SYQ72-2M04UV0enenT3G1wDVH5boQIufSY1MQ2ghM1Ved_LSDK8YldU";
		Instant expiryDate = Instant.now();
		UserEntity user = new UserEntity();

		// Act
		RefreshTokenEntity refreshToken = new RefreshTokenEntity(id, token, user);

		// Assert
		assertNotNull(refreshToken);
		assertEquals(id, refreshToken.getId());
		assertEquals(token, refreshToken.getToken());
		assertEquals(user, refreshToken.getUser());
	}
}
