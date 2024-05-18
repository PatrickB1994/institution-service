package com.mdsl.institutionservice.enums;

import lombok.Getter;

@Getter
public enum ExceptionCode
{
	AccessDeniedException(1000, "AccessDeniedException"),
	METHOD_NOT_SUPPORTED_EXCEPTION(1001, "METHOD_NOT_SUPPORTED_EXCEPTION"),
	GENERAL_EXCEPTION(4000, "GENERAL_EXCEPTION"),
	ENTITY_NOT_FOUND(4001, "ENTITY_NOT_FOUND"),
	VALIDATION_EXCEPTION(4002, "VALIDATION_EXCEPTION"),
	TOKEN_EXPIRED_EXCEPTION(4003, "TOKEN_EXPIRED_EXCEPTION"),
	INVALID_REQUEST_EXCEPTION(4004, "INVALID_REQUEST_EXCEPTION"),
	;

	private final int code;
	private final String message;

	private ExceptionCode(int code, String message)
	{
		this.code = code;
		this.message = message;
	}

}
