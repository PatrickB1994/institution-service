package com.mdsl.institutionservice.service;

import com.mdsl.institutionservice.entity.UserEntity;

public interface RefreshTokenService
{
	void deleteUserRefreshToken(UserEntity user);
}
