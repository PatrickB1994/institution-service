package com.mdsl.institutionservice.service.implementation;

import com.mdsl.institutionservice.entity.UserEntity;
import com.mdsl.institutionservice.repository.RefreshTokenRepository;
import com.mdsl.institutionservice.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenImpl implements RefreshTokenService
{
	private final RefreshTokenRepository refreshTokenRepository;

	@Override
	public void deleteUserRefreshToken(UserEntity user)
	{
		refreshTokenRepository.deleteByUser(user);
	}
}
