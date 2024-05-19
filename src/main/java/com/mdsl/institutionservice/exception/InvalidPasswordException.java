package com.mdsl.institutionservice.exception;

import com.mdsl.institutionservice.enums.ExceptionCode;

public class InvalidPasswordException extends BaseException
{

	public InvalidPasswordException(String message)
	{
		super(ExceptionCode.INVALID_PASSWORD_EXCEPTION, message);
	}
}
