package com.mdsl.institutionservice.enums;

import lombok.Getter;

@Getter
public enum ExceptionCode
{
	ACCESS_DENIED_EXCEPTION(1000, "Access denied"),
	METHOD_NOT_SUPPORTED_EXCEPTION(1001, "Method not supported"),
	AUTHENTICATION_FAILED_EXCEPTION(1002, "Authentication failed"),
	AUTHORIZATION_FAILED_EXCEPTION(1003, "Authorization failed"),
	GENERAL_EXCEPTION(4000, "Error occurred"),
	ENTITY_NOT_FOUND(4001, "Entity not found"),
	VALIDATION_EXCEPTION(4002, "Error validating"),
	TOKEN_EXPIRED_EXCEPTION(4003, "Token expired"),
	INVALID_REQUEST_EXCEPTION(4004, "Invalid request"),
	INVALID_USER_NAME_EXCEPTION(4005, "Invalid username"),
	INVALID_PASSWORD_EXCEPTION(4006, "Invalid password"),
	INVALID_ROLE_EXCEPTION(4007, "Invalid role"),
	;

	private final int code;
	private final String message;

	private ExceptionCode(int code, String message)
	{
		this.code = code;
		this.message = message;
	}

}
