package com.mdsl.institutionservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseStatus
{
	SUCCESS("Success"),
	FAILED("Failed");

	private final String status;

}
