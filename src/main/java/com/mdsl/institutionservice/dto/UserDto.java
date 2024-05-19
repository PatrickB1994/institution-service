package com.mdsl.institutionservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto
{
	private Long id;
	private String userName;
	private String password;
	private List<String> roles;
}
