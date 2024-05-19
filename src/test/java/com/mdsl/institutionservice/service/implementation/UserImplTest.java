package com.mdsl.institutionservice.service.implementation;

import com.mdsl.institutionservice.dto.BaseResponse;
import com.mdsl.institutionservice.dto.UserDto;
import com.mdsl.institutionservice.entity.RoleEntity;
import com.mdsl.institutionservice.entity.UserEntity;
import com.mdsl.institutionservice.enums.ResponseStatus;
import com.mdsl.institutionservice.exception.EntityNotFoundException;
import com.mdsl.institutionservice.exception.InvalidRequestException;
import com.mdsl.institutionservice.repository.UserRepository;
import com.mdsl.institutionservice.service.RoleService;
import com.mdsl.institutionservice.shared.Validation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserImplTest
{
	@Mock
	private Validation validation;

	@Mock
	private UserRepository userRepository;

	@Mock
	private RoleService roleService;

	@InjectMocks
	private UserImpl userService;

	private UserEntity user;
	private UserDto userDto;

	@BeforeEach
	void setUp()
	{
		user = new UserEntity();
		user.setId(1L);
		user.setName("testuser");
		user.setPassword(new BCryptPasswordEncoder().encode("password"));
		user.setRoles(Collections.singletonList(new RoleEntity(1L, "USER")));

		userDto = new UserDto();
		userDto.setId(1L);
		userDto.setUserName("testuser");
		userDto.setPassword("password");
		userDto.setRoles(Collections.singletonList("USER"));
	}

	@Test
	void testGetUserByName_UserExists()
	{
		when(userRepository.findByName("testuser")).thenReturn(Optional.of(user));

		UserEntity foundUser = userService.getUserByName("testuser");

		assertNotNull(foundUser);
		assertEquals("testuser", foundUser.getName());
	}

	@Test
	void testGetUserByName_UserNotFound()
	{
		when(userRepository.findByName("testuser")).thenReturn(Optional.empty());

		assertThrows(EntityNotFoundException.class, () -> userService.getUserByName("testuser"));
	}

	@Test
	void testGetUser()
	{
		Pageable pageable = PageRequest.of(0, 10);
		Page<UserEntity> userPage = new PageImpl<>(Collections.singletonList(user));

		when(userRepository.findByIdOrNameOrAll(1L, "testuser", pageable)).thenReturn(userPage);

		BaseResponse<Page<UserEntity>> response = userService.getUser(1L, "testuser", pageable);

		assertNotNull(response);
		assertEquals(ResponseStatus.SUCCESS.getStatus(), response.getDeveloperMessage());
		assertEquals(1, response.getEntity().getTotalElements());
	}

	@Test
	void testAddOrUpdateUser_CreateNewUser()
	{
		userDto.setId(null);
		when(userRepository.findByName("testuser")).thenReturn(Optional.empty());
		when(userRepository.save(any(UserEntity.class))).thenReturn(user);

		BaseResponse<UserEntity> response = userService.addOrUpdateUser(userDto);

		assertNotNull(response);
		assertEquals(ResponseStatus.SUCCESS.getStatus(), response.getDeveloperMessage());
		assertEquals("User added successfully", response.getMessage());
		assertEquals("testuser", response.getEntity().getName());
	}

	@Test
	void testAddOrUpdateUser_UpdateExistingUser()
	{
		when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		when(userRepository.save(any(UserEntity.class))).thenReturn(user);

		BaseResponse<UserEntity> response = userService.addOrUpdateUser(userDto);

		assertNotNull(response);
		assertEquals(ResponseStatus.SUCCESS.getStatus(), response.getDeveloperMessage());
		assertEquals("User updated successfully", response.getMessage());
		assertEquals("testuser", response.getEntity().getName());
	}

	@Test
	void testDeleteUser()
	{
		String currentUserName = "admin";
		UserEntity adminUser = new UserEntity();
		adminUser.setId(2L);
		adminUser.setName(currentUserName);

		when(userRepository.findByName(currentUserName)).thenReturn(Optional.of(adminUser));
		when(userRepository.findById(1L)).thenReturn(Optional.of(user));

		BaseResponse<?> response = userService.deleteUser(1L, currentUserName);

		assertNotNull(response);
		assertEquals(ResponseStatus.SUCCESS.getStatus(), response.getDeveloperMessage());
		assertEquals("User deleted successfully", response.getMessage());
		verify(userRepository, times(1)).deleteById(1L);
	}

	@Test
	void testDeleteUser_UserNotFound()
	{
		String currentUserName = "admin";

		when(userRepository.findByName(currentUserName)).thenReturn(Optional.of(user));

		assertThrows(InvalidRequestException.class, () -> userService.deleteUser(1L, currentUserName));
	}

	@Test
	void testDeleteUser_DeleteSelf()
	{
		String currentUserName = "testuser";

		when(userRepository.findByName(currentUserName)).thenReturn(Optional.of(user));

		assertThrows(InvalidRequestException.class, () -> userService.deleteUser(1L, currentUserName));
	}
	//
	//	@Test
	//	public void testGetUserByName_ExistingUser()
	//	{
	//		// Arrange
	//		String userName = "admin";
	//		String encodedPassword = new BCryptPasswordEncoder().encode("admin");
	//		UserEntity userEntity = UserEntity.builder()
	//										  .name(userName)
	//										  .password(encodedPassword)
	//										  .roles(Collections.singletonList(RoleEntity.builder().name("ADMIN").build()))
	//										  .build();
	//		when(userRepository.findByName(userName)).thenReturn(Optional.of(userEntity));
	//
	//		// Act
	//		UserEntity retrievedUser = userService.getUserByName(userName);
	//
	//		// Assert
	//		assertEquals(userName, retrievedUser.getName());
	//		assertEquals(encodedPassword, retrievedUser.getPassword());
	//		assertEquals("ADMIN", retrievedUser.getRoles().getFirst().getName());
	//		verify(userRepository, times(1)).findByName(userName);
	//	}
	//
	//	@Test
	//	public void testGetUserByName_NonExistingUser()
	//	{
	//		// Arrange
	//		String userName = "non_existing_user";
	//		when(userRepository.findByName(userName)).thenReturn(Optional.empty());
	//
	//		// Act and Assert
	//		assertThrows(EntityNotFoundException.class, () -> userService.getUserByName(userName));
	//		verify(userRepository, times(1)).findByName(userName);
	//	}
}
