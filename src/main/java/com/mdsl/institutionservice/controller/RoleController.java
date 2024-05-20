package com.mdsl.institutionservice.controller;

import com.mdsl.institutionservice.dto.BaseResponse;
import com.mdsl.institutionservice.entity.RoleEntity;
import com.mdsl.institutionservice.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/role")
@RequiredArgsConstructor
public class RoleController
{
	private final RoleService roleService;

	@Operation(summary = "Get roles", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("v1")
	public BaseResponse<Page<RoleEntity>> getRoles(@RequestParam(required = false) String name, Pageable pageable)
	{
		return roleService.getRoles(name, pageable);
	}
}
