package com.mdsl.institutionservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseResponseTest
{

	@JsonIgnore
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	@Test
	public void testGettersAndSetters()
	{
		// Arrange
		BaseResponse<String> response = new BaseResponse<>();
		String entity = "Test Entity";
		String message = "Test Message";
		String developerMessage = "Test Developer Message";
		int statusCode = 200;
		String timestamp = LocalDateTime.now().format(formatter);

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
	public void testChainedAccessors()
	{
		// Arrange
		BaseResponse<String> response = new BaseResponse<>();
		String entity = "Test Entity";
		String message = "Test Message";
		String developerMessage = "Test Developer Message";
		int statusCode = 200;
		String timestamp = LocalDateTime.now().format(formatter);

		// Act
		response.setEntity(entity).setMessage(message).setDeveloperMessage(developerMessage).setStatusCode(statusCode).setTimestamp(timestamp);

		// Assert
		assertEquals(entity, response.getEntity());
		assertEquals(message, response.getMessage());
		assertEquals(developerMessage, response.getDeveloperMessage());
		assertEquals(statusCode, response.getStatusCode());
		assertEquals(timestamp, response.getTimestamp());
	}

}
