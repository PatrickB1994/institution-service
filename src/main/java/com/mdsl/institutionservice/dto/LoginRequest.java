package com.mdsl.institutionservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest
{
	private String userName;
	private String password;
}
