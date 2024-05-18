package com.mdsl.institutionservice.exception;

import com.mdsl.institutionservice.enums.ExceptionCode;

public class EntityNotFoundException extends BaseException
{
	public EntityNotFoundException()
	{
		super(ExceptionCode.ENTITY_NOT_FOUND);
	}

	public EntityNotFoundException(String className)
	{
		super(className + " not found");
	}
}
