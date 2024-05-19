package com.mdsl.institutionservice.security;

import com.mdsl.institutionservice.entity.RoleEntity;
import com.mdsl.institutionservice.entity.UserEntity;
import com.mdsl.institutionservice.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{

	private final UserRepository repository;

	public UserDetailsServiceImpl(UserRepository repository)
	{
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String name)
	{

		UserEntity user = repository.findByName(name).orElseThrow(() -> new UsernameNotFoundException("Username not fount"));

		return org.springframework.security.core.userdetails.User.builder()
																 .username(user.getName())
																 .password(user.getPassword())
																 .roles(user.getRoles()
																			.stream()
																			.map(RoleEntity::getName)
																			.collect(Collectors.joining(", ")))
																 .build();
	}
}
