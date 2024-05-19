package com.mdsl.institutionservice.exception;

import com.mdsl.institutionservice.enums.ExceptionCode;

public class InvalidRoleException extends BaseException
{

	public InvalidRoleException()
	{
		super(ExceptionCode.INVALID_ROLE_EXCEPTION);
	}
}
