package com.mdsl.institutionservice.controller;

import com.mdsl.institutionservice.dto.BaseResponse;
import com.mdsl.institutionservice.dto.UserDto;
import com.mdsl.institutionservice.entity.UserEntity;
import com.mdsl.institutionservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController
{
	private final UserService userService;

	@Operation(summary = "Get users", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("v1")
	public BaseResponse<Page<UserEntity>> getUser(@RequestParam(required = false) Long id, @RequestParam(required = false) String name,
			Pageable pageable)
	{
		return userService.getUser(id, name, pageable);
	}

	@Operation(summary = "Add Or Update User", security = @SecurityRequirement(name = "bearerAuth"))
	@PostMapping("v1")
	public BaseResponse<UserEntity> addOrUpdateUser(@RequestBody UserDto userDto)
	{
		return userService.addOrUpdateUser(userDto);
	}

	@Operation(summary = "Delete User", security = @SecurityRequirement(name = "bearerAuth"))
	@DeleteMapping("v1")
	public BaseResponse<?> deleteUser(@RequestParam(required = true) Long id, @AuthenticationPrincipal UserDetails userDetails)
	{
		return userService.deleteUser(id, userDetails.getUsername());
	}
}
