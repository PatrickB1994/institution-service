package com.mdsl.institutionservice.service.implementation;

import com.mdsl.institutionservice.dto.BaseResponse;
import com.mdsl.institutionservice.entity.RoleEntity;
import com.mdsl.institutionservice.enums.ResponseStatus;
import com.mdsl.institutionservice.exception.EntityNotFoundException;
import com.mdsl.institutionservice.repository.RoleRepository;
import com.mdsl.institutionservice.service.RoleService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	public BaseResponse<Page<RoleEntity>> getRoles(String roleName, Pageable pageable)
	{
		BaseResponse<Page<RoleEntity>> response = new BaseResponse<>();
		Page<RoleEntity> roles = roleRepository.findAllOrByName(roleName, pageable);

		response.setEntity(roles).setDeveloperMessage(ResponseStatus.SUCCESS.getStatus()).setMessage("Roles retrieved successfully");
		return response;
	}

	public RoleEntity getByName(String roleName)
	{
		return roleRepository.findByName(roleName).orElseThrow(() -> new EntityNotFoundException("Role"));
	}

	public RoleEntity getById(Long roleId)
	{
		return roleRepository.findById(roleId).orElseThrow(() -> new EntityNotFoundException("Role"));
	}

}
