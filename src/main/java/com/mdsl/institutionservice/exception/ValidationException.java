package com.mdsl.institutionservice.exception;

import com.mdsl.institutionservice.enums.ExceptionCode;

public class ValidationException extends BaseException
{

	public ValidationException(String message)
	{
		super(ExceptionCode.VALIDATION_EXCEPTION, message);
	}
}
