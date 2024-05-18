package com.mdsl.institutionservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class LoginResponse
{
	private String token;
	private String refreshToken;
}
