package com.mdsl.institutionservice.dto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDtoTest
{

	@Test
	void testGettersAndSetters()
	{
		Long id = 1L;
		String userName = "testuser";
		String password = "password";
		List<Long> roles = Arrays.asList(1L, 2L);

		UserDto userDto = new UserDto();
		userDto.setId(id);
		userDto.setUserName(userName);
		userDto.setPassword(password);
		userDto.setRoles(roles);

		assertEquals(id, userDto.getId());
		assertEquals(userName, userDto.getUserName());
		assertEquals(password, userDto.getPassword());
		assertEquals(roles, userDto.getRoles());
	}
}
