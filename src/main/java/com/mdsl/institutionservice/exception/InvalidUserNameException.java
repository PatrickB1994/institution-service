package com.mdsl.institutionservice.exception;

import com.mdsl.institutionservice.enums.ExceptionCode;

public class InvalidUserNameException extends BaseException
{

	public InvalidUserNameException()
	{
		super(ExceptionCode.INVALID_USER_NAME_EXCEPTION);
	}
}
