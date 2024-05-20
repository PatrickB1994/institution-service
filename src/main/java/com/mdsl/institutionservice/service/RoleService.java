package com.mdsl.institutionservice.service;

import com.mdsl.institutionservice.dto.BaseResponse;
import com.mdsl.institutionservice.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleService
{
	BaseResponse<Page<RoleEntity>> getRoles(String roleName, Pageable pageable);

	RoleEntity getByName(String roleName);

	RoleEntity getById(Long roleId);
}
