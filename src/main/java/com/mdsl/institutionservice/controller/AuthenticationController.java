package com.mdsl.institutionservice.controller;

import com.mdsl.institutionservice.dto.BaseResponse;
import com.mdsl.institutionservice.dto.LoginRequest;
import com.mdsl.institutionservice.dto.LoginResponse;
import com.mdsl.institutionservice.dto.RefreshTokenRequest;
import com.mdsl.institutionservice.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthenticationController
{

	private final AuthenticationService authenticationService;

	@PostMapping("/login/v1")
	public BaseResponse<LoginResponse> login(@RequestBody LoginRequest loginRequest)
	{
		return authenticationService.login(loginRequest);
	}

	@PostMapping("/refreshToken/v1")
	public BaseResponse<LoginResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest)
	{
		return authenticationService.refreshToken(refreshTokenRequest);
	}
}
