package com.mdsl.institutionservice.exception;

import com.mdsl.institutionservice.enums.ExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AccessDeniedException extends BaseException
{

	public AccessDeniedException(String message)
	{
		super(ExceptionCode.VALIDATION_EXCEPTION, message);
	}
}
