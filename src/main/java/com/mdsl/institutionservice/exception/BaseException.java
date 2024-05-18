package com.mdsl.institutionservice.exception;

import com.mdsl.institutionservice.enums.ExceptionCode;

public class BaseException extends RuntimeException
{
	private final ExceptionCode exceptionCode;

	public BaseException(ExceptionCode exceptionCode)
	{
		super(exceptionCode.getMessage());
		this.exceptionCode = exceptionCode;
	}

	public BaseException(ExceptionCode exceptionCode, String message)
	{
		super(message);
		this.exceptionCode = exceptionCode;
	}

	public BaseException(String message) {
		super(message);
		this.exceptionCode = ExceptionCode.GENERAL_EXCEPTION;
	}
}
