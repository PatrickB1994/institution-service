package com.mdsl.institutionservice.service.implementation;

import com.mdsl.institutionservice.entity.RoleEntity;
import com.mdsl.institutionservice.exception.EntityNotFoundException;
import com.mdsl.institutionservice.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class RoleImplTest
{

	@Mock
	private RoleRepository roleRepository;

	@InjectMocks
	private RoleImpl roleService;

	@BeforeEach
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetByName()
	{
		// Arrange
		String roleName = "ADMIN";
		RoleEntity expectedRole = RoleEntity.builder().id(1L).name(roleName).build();
		when(roleRepository.findByName(roleName)).thenReturn(Optional.of(expectedRole));

		// Act
		RoleEntity actualRole = roleService.getByName(roleName);

		// Assert
		assertEquals(expectedRole, actualRole);
	}

	@Test
	public void testGetByNameWhenRoleNotFound()
	{
		// Arrange
		String roleName = "INVALID_ROLE";
		when(roleRepository.findByName(roleName)).thenReturn(Optional.empty());

		// Act and Assert
		// Ensure that EntityNotFoundException is thrown when role is not found
		assertThrows(EntityNotFoundException.class, () -> roleService.getByName(roleName));
	}
}
