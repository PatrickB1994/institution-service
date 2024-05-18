package com.mdsl.institutionservice.service;

import com.mdsl.institutionservice.dto.BaseResponse;
import com.mdsl.institutionservice.dto.LoginRequest;
import com.mdsl.institutionservice.dto.LoginResponse;
import com.mdsl.institutionservice.dto.RefreshTokenRequest;

public interface AuthenticationService
{
	BaseResponse<LoginResponse> login(LoginRequest loginRequest);

	BaseResponse<LoginResponse> refreshToken(RefreshTokenRequest refreshTokenRequest);
}
