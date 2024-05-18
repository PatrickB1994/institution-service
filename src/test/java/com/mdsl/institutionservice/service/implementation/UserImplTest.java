package com.mdsl.institutionservice.service.implementation;

import com.mdsl.institutionservice.entity.UserEntity;
import com.mdsl.institutionservice.exception.EntityNotFoundException;
import com.mdsl.institutionservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserImplTest
{

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserImpl userService;

	@BeforeEach
	public void setUp()
	{
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetUserByName_ExistingUser()
	{
		// Arrange
		String userName = "admin";
		String encodedPassword = new BCryptPasswordEncoder().encode("admin");
		UserEntity userEntity = UserEntity.builder().name(userName).password(encodedPassword).roles("ADMIN").build();
		when(userRepository.findByName(userName)).thenReturn(Optional.of(userEntity));

		// Act
		UserEntity retrievedUser = userService.getUserByName(userName);

		// Assert
		assertEquals(userName, retrievedUser.getName());
		assertEquals(encodedPassword, retrievedUser.getPassword());
		assertEquals("ADMIN", retrievedUser.getRoles());
		verify(userRepository, times(1)).findByName(userName);
	}

	@Test
	public void testGetUserByName_NonExistingUser()
	{
		// Arrange
		String userName = "non_existing_user";
		when(userRepository.findByName(userName)).thenReturn(Optional.empty());

		// Act and Assert
		assertThrows(EntityNotFoundException.class, () -> userService.getUserByName(userName));
		verify(userRepository, times(1)).findByName(userName);
	}
}
