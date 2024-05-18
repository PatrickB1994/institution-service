package com.mdsl.institutionservice.exception;

import com.mdsl.institutionservice.enums.ExceptionCode;

public class AccessDeniedException extends BaseException
{

	public AccessDeniedException(String message)
	{
		super(ExceptionCode.VALIDATION_EXCEPTION, message);
	}
}
