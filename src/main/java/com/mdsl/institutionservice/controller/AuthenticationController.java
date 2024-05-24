package com.mdsl.institutionservice.controller;

import com.mdsl.institutionservice.dto.BaseResponse;
import com.mdsl.institutionservice.dto.LoginRequest;
import com.mdsl.institutionservice.dto.LoginResponse;
import com.mdsl.institutionservice.dto.RefreshTokenRequest;
import com.mdsl.institutionservice.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

	@Operation(summary = "Login User")
	@PostMapping("/login/v1")
	public BaseResponse<LoginResponse> login(@RequestBody LoginRequest loginRequest)
	{
		return authenticationService.login(loginRequest);
	}

	@Operation(summary = "Refresh User Token")
	@PostMapping("/refreshToken/v1")
	public BaseResponse<LoginResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest)
	{
		return authenticationService.refreshToken(refreshTokenRequest);
	}

	@Operation(summary = "Logout User", security = @SecurityRequirement(name = "bearerAuth"))
	@PostMapping("/logout/v1")
	public BaseResponse<?> logout(@AuthenticationPrincipal UserDetails userDetails)
	{
		return authenticationService.logout(userDetails);
	}
}
