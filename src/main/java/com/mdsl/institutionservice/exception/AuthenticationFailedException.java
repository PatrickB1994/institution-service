package com.mdsl.institutionservice.exception;

import com.mdsl.institutionservice.enums.ExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AuthenticationFailedException extends BaseException
{

	public AuthenticationFailedException()
	{
		super(ExceptionCode.AUTHENTICATION_FAILED_EXCEPTION);
	}
}
