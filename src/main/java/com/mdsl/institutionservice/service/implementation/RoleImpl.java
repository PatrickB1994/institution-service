package com.mdsl.institutionservice.service.implementation;

import com.mdsl.institutionservice.entity.RoleEntity;
import com.mdsl.institutionservice.exception.EntityNotFoundException;
import com.mdsl.institutionservice.repository.RoleRepository;
import com.mdsl.institutionservice.service.RoleService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleImpl implements RoleService
{
	private final RoleRepository roleRepository;

	/**
	 * This method is populating the users for testing purposes
	 **/
	@PostConstruct
	private void init()
	{
		List<RoleEntity> roles = new ArrayList<>();
		new RoleEntity();
		roles.add(RoleEntity.builder().name("ADMIN").build());
		roles.add(RoleEntity.builder().name("USER").build());
		roleRepository.saveAll(roles);
	}

	public RoleEntity getByName(String roleName)
	{
		return roleRepository.findByName(roleName).orElseThrow(() -> new EntityNotFoundException("Role"));
	}

}
