package com.mdsl.institutionservice.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoleEntityTest
{

	@Test
	public void testRoleEntityConstructorAndGetters()
	{
		// Arrange
		Long id = 1L;
		String name = "ROLE_ADMIN";

		// Act
		RoleEntity roleEntity = new RoleEntity(id, name);

		// Assert
		assertEquals(id, roleEntity.getId());
		assertEquals(name, roleEntity.getName());
	}

	@Test
	public void testRoleEntitySetters()
	{
		// Arrange
		RoleEntity roleEntity = new RoleEntity();
		Long id = 1L;
		String name = "ROLE_USER";

		// Act
		roleEntity.setId(id);
		roleEntity.setName(name);

		// Assert
		assertEquals(id, roleEntity.getId());
		assertEquals(name, roleEntity.getName());
	}

	@Test
	public void testRoleEntityBuilder()
	{
		// Arrange
		Long id = 1L;
		String name = "ROLE_ADMIN";

		// Act
		RoleEntity roleEntity = RoleEntity.builder().id(id).name(name).build();

		// Assert
		assertEquals(id, roleEntity.getId());
		assertEquals(name, roleEntity.getName());
	}
}
