package com.mdsl.institutionservice.exception;

import com.mdsl.institutionservice.enums.ExceptionCode;

public class TokenExpiredException extends BaseException
{

	public TokenExpiredException(String message)
	{
		super(ExceptionCode.TOKEN_EXPIRED_EXCEPTION, message);
	}
}
