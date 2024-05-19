package com.mdsl.institutionservice.service;

import com.mdsl.institutionservice.entity.RoleEntity;

public interface RoleService
{
	RoleEntity getByName(String roleName);
}
