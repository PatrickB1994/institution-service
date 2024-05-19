package com.mdsl.institutionservice.service.implementation;

import com.mdsl.institutionservice.entity.RoleEntity;
import com.mdsl.institutionservice.entity.UserEntity;
import com.mdsl.institutionservice.exception.EntityNotFoundException;
import com.mdsl.institutionservice.repository.UserRepository;
import com.mdsl.institutionservice.service.RoleService;
import com.mdsl.institutionservice.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserImpl implements UserService
{

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

}
