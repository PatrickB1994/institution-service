package com.mdsl.institutionservice.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserEntityTest
{

	@Test
	public void testGetterAndSetterMethods()
	{
		// Arrange
		Long id = 1L;
		String name = "testUser";
		String password = "testPassword";
		String roles = "ROLE_USER";

		UserEntity user = new UserEntity();

		// Act
		user.setId(id);
		user.setName(name);
		user.setPassword(password);
		user.setRoles(roles);

		// Assert
		assertEquals(id, user.getId());
		assertEquals(name, user.getName());
		assertEquals(password, user.getPassword());
		assertEquals(roles, user.getRoles());
	}

	@Test
	public void testBuilder()
	{
		// Arrange
		Long id = 1L;
		String name = "testUser";
		String password = "testPassword";
		String roles = "ROLE_USER";

		// Act
		UserEntity user = UserEntity.builder().id(id).name(name).password(password).roles(roles).build();

		// Assert
		assertNotNull(user);
		assertEquals(id, user.getId());
		assertEquals(name, user.getName());
		assertEquals(password, user.getPassword());
		assertEquals(roles, user.getRoles());
	}

	@Test
	public void testAllArgsConstructor()
	{
		// Arrange
		Long id = 1L;
		String name = "testUser";
		String password = "testPassword";
		String roles = "ROLE_USER";

		// Act
		UserEntity user = new UserEntity(id, name, password, roles);

		// Assert
		assertNotNull(user);
		assertEquals(id, user.getId());
		assertEquals(name, user.getName());
		assertEquals(password, user.getPassword());
		assertEquals(roles, user.getRoles());
	}
}
