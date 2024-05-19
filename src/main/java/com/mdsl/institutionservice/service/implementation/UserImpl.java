package com.mdsl.institutionservice.service.implementation;

import com.mdsl.institutionservice.dto.BaseResponse;
import com.mdsl.institutionservice.dto.UserDto;
import com.mdsl.institutionservice.entity.RoleEntity;
import com.mdsl.institutionservice.entity.UserEntity;
import com.mdsl.institutionservice.enums.ResponseStatus;
import com.mdsl.institutionservice.exception.EntityNotFoundException;
import com.mdsl.institutionservice.exception.InvalidRequestException;
import com.mdsl.institutionservice.exception.InvalidUserNameException;
import com.mdsl.institutionservice.repository.UserRepository;
import com.mdsl.institutionservice.service.RoleService;
import com.mdsl.institutionservice.service.UserService;
import com.mdsl.institutionservice.shared.Validation;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserImpl implements UserService
{

	private final Validation validation;
	private final UserRepository userRepository;
	private final RoleService roleService;

	/**
	 * This method is populating the users for testing purposes
	 **/
	@PostConstruct
	private void init()
	{

		List<RoleEntity> adminRoles = List.of(roleService.getByName("ADMIN"));
		List<RoleEntity> userRoles = List.of(roleService.getByName("USER"));
		List<UserEntity> users = new ArrayList<>();
		users.add(UserEntity.builder().name("admin").password(new BCryptPasswordEncoder().encode("admin")).roles(adminRoles).build());
		users.add(UserEntity.builder().name("user").password(new BCryptPasswordEncoder().encode("user")).roles(userRoles).build());
		userRepository.saveAll(users);
	}

	/**
	 * This method is retrieves users by name and throws an exception if not found
	 *
	 * @param userName the name of user to be retrieved
	 * @return UserEntity containing all the user info
	 **/
	@Override
	public UserEntity getUserByName(String userName)
	{
		return userRepository.findByName(userName).orElseThrow(EntityNotFoundException::new);
	}

	/**
	 * This method is responsible for retrieving user by ID, name or all
	 *
	 * @param id   the user ID to filter by
	 * @param name the  username to filter by
	 * @return BaseResponse<List < UserEntity>> containing the list of user entities
	 **/
	@Override
	public BaseResponse<Page<UserEntity>> getUser(Long id, String name, Pageable pageable)
	{
		BaseResponse<Page<UserEntity>> response = new BaseResponse<>();

		Page<UserEntity> users = userRepository.findByIdOrNameOrAll(id, name, pageable);

		response.setEntity(users).setDeveloperMessage(ResponseStatus.SUCCESS.getStatus()).setMessage("User retrieved successfully");
		return response;
	}

	/**
	 * This method is responsible for creating and updating users
	 *
	 * @param userDto the DTO containing user information
	 * @return BaseResponse<UserEntity> containing the saved or updated entity
	 **/
	@Override
	public BaseResponse<UserEntity> addOrUpdateUser(UserDto userDto)
	{
		Long userId = userDto.getId();
		boolean isUpdate = userId != null && userId != 0;

		// Validate institution data
		validation.validateAddOrUpdateUser(userDto);

		// Initialize response and entity
		BaseResponse<UserEntity> response = new BaseResponse<>();
		UserEntity userEntity = new UserEntity();

		// Handle update case
		if(isUpdate)
		{
			userEntity = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User ID: " + userId));
		}
		else
		{
			if(userRepository.findByName(userDto.getUserName()).isPresent())
			{
				throw new InvalidRequestException("Username already exist");
			}
		}

		// Update fields with non-null values from DTO
		updateUserFields(userDto, userEntity);

		// Save entity to repository
		userEntity = userRepository.save(userEntity);

		response.setEntity(userEntity)
				.setDeveloperMessage(ResponseStatus.SUCCESS.getStatus())
				.setMessage(isUpdate ? "User updated successfully" : "User added successfully");
		return response;
	}

	/**
	 * This method is responsible for deleting user by ID
	 *
	 * @param id the user ID to be deleted
	 * @return BaseResponse
	 **/
	@Override
	public BaseResponse<?> deleteUser(Long id, String currentUserName)
	{
		BaseResponse<List<UserEntity>> response = new BaseResponse<>();

		validation.validateDeleteInstitution(id);

		UserEntity currentUser = userRepository.findByName(currentUserName).orElseThrow(InvalidUserNameException::new);

		if(Objects.equals(currentUser.getId(), id))
		{
			throw new InvalidRequestException("User cannot delete himself");
		}

		userRepository.findById(id).orElseThrow(EntityNotFoundException::new);

		userRepository.deleteById(id);

		response.setDeveloperMessage(ResponseStatus.SUCCESS.getStatus()).setMessage("User deleted successfully");
		return response;
	}

	/**
	 * This method is used to update the non-null fields
	 *
	 * @param dto    the DTO containing user information
	 * @param entity the entity which will be updated
	 **/
	private void updateUserFields(UserDto dto, UserEntity entity)
	{
		if(dto.getUserName() != null)
		{
			entity.setName(dto.getUserName());
		}
		if(dto.getPassword() != null)
		{
			entity.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
		}
		if(dto.getRoles() != null && !dto.getRoles().isEmpty())
		{
			entity.setRoles(dto.getRoles().stream().map(roleService::getByName).collect(Collectors.toList()));
		}
	}

}
