package com.mdsl.institutionservice.exception;

import com.mdsl.institutionservice.enums.ExceptionCode;

public class InvalidRequestException extends BaseException
{

	public InvalidRequestException(String message)
	{
		super(ExceptionCode.INVALID_REQUEST_EXCEPTION, message);
	}
}
