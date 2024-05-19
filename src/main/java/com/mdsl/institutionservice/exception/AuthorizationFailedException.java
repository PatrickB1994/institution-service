package com.mdsl.institutionservice.exception;

import com.mdsl.institutionservice.enums.ExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthorizationFailedException extends BaseException
{

	public AuthorizationFailedException()
	{
		super(ExceptionCode.AUTHORIZATION_FAILED_EXCEPTION);
	}
}
