package com.mdsl.institutionservice.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class BaseResponseTest {

	@Test
	public void testGettersAndSetters() {
		// Arrange
		BaseResponse<String> response = new BaseResponse<>();
		String entity = "Test Entity";
		String message = "Test Message";
		String developerMessage = "Test Developer Message";
		int statusCode = 200;
		LocalDateTime timestamp = LocalDateTime.now();

		// Act
		response.setEntity(entity);
		response.setMessage(message);
		response.setDeveloperMessage(developerMessage);
		response.setStatusCode(statusCode);
		response.setTimestamp(timestamp);

		// Assert
		assertEquals(entity, response.getEntity());
		assertEquals(message, response.getMessage());
		assertEquals(developerMessage, response.getDeveloperMessage());
		assertEquals(statusCode, response.getStatusCode());
		assertEquals(timestamp, response.getTimestamp());
	}

	@Test
	public void testChainedAccessors() {
		// Arrange
		BaseResponse<String> response = new BaseResponse<>();
		String entity = "Test Entity";
		String message = "Test Message";
		String developerMessage = "Test Developer Message";
		int statusCode = 200;
		LocalDateTime timestamp = LocalDateTime.now();

		// Act
		response.setEntity(entity)
				.setMessage(message)
				.setDeveloperMessage(developerMessage)
				.setStatusCode(statusCode)
				.setTimestamp(timestamp);

		// Assert
		assertEquals(entity, response.getEntity());
		assertEquals(message, response.getMessage());
		assertEquals(developerMessage, response.getDeveloperMessage());
		assertEquals(statusCode, response.getStatusCode());
		assertEquals(timestamp, response.getTimestamp());
	}

	@Test
	public void testDefaultTimestamp() {
		// Arrange
		BaseResponse<String> response = new BaseResponse<>();

		// Act
		LocalDateTime timestamp = response.getTimestamp();

		// Assert
		assertNotNull(timestamp);
		assertTrue(timestamp.isBefore(LocalDateTime.now().plusSeconds(1)));
	}
}
