package com.mdsl.institutionservice.service.implementation;

import com.mdsl.institutionservice.dto.BaseResponse;
import com.mdsl.institutionservice.dto.LoginRequest;
import com.mdsl.institutionservice.dto.LoginResponse;
import com.mdsl.institutionservice.dto.RefreshTokenRequest;
import com.mdsl.institutionservice.entity.RefreshTokenEntity;
import com.mdsl.institutionservice.entity.UserEntity;
import com.mdsl.institutionservice.enums.ResponseStatus;
import com.mdsl.institutionservice.exception.AccessDeniedException;
import com.mdsl.institutionservice.exception.TokenExpiredException;
import com.mdsl.institutionservice.repository.RefreshTokenRepository;
import com.mdsl.institutionservice.security.JwtHelper;
import com.mdsl.institutionservice.service.AuthenticationService;
import com.mdsl.institutionservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationImpl implements AuthenticationService
{

	private final AuthenticationManager authenticationManager;
	private final RefreshTokenRepository refreshTokenRepository;
	private final UserService userService;

	/**
	 * This method is responsible for handling user authentication by username and password
	 *
	 * @param loginRequest the DTO containing user credentials
	 * @return BaseResponse<LoginResponse> containing the token and refresh token
	 **/
	@Override
	public BaseResponse<LoginResponse> login(LoginRequest loginRequest)
	{
		BaseResponse<LoginResponse> response = new BaseResponse<>();

		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
		String token = JwtHelper.generateToken(loginRequest.getUserName());
		LoginResponse loginResponse = new LoginResponse().setToken(token).setRefreshToken(createRefreshToken(loginRequest.getUserName()).getToken());

		response.setEntity(loginResponse).setMessage("Login successful").setMessage(ResponseStatus.SUCCESS.getStatus());
		return response;
	}

	/**
	 * This method is responsible for handling refresh token
	 *
	 * @param refreshTokenRequest the DTO containing user credentials
	 * @return BaseResponse<LoginResponse> containing the new token and refresh token
	 **/
	@Override
	public BaseResponse<LoginResponse> refreshToken(RefreshTokenRequest refreshTokenRequest)
	{
		BaseResponse<LoginResponse> response = new BaseResponse<>();

		RefreshTokenEntity refreshToken = refreshTokenRepository.findByToken(refreshTokenRequest.getRefreshToken())
																.orElseThrow(() -> new AccessDeniedException("Refresh token not found"));

		verifyRefreshTokenExpiration(refreshToken);
		String token = JwtHelper.generateToken(refreshToken.getUser().getName());

		return response.setMessage(ResponseStatus.SUCCESS.getStatus())
					   .setEntity(new LoginResponse().setToken(token).setRefreshToken(refreshTokenRequest.getRefreshToken()));
	}

	/**
	 * This method validates the refresh token expiration
	 *
	 * @param refreshToken the DTO containing user credentials
	 **/
	private void verifyRefreshTokenExpiration(RefreshTokenEntity refreshToken)
	{
		if(refreshToken.getExpiryDate().compareTo(Instant.now()) < 0)
		{
			refreshTokenRepository.delete(refreshToken);
			throw new TokenExpiredException("Token was expired, please sign in again");
		}
	}

	/**
	 * This method is responsible for creating refresh token by user
	 *
	 * @param userName of which the token belong to
	 * @return RefreshTokenEntity containing the new token and refresh token
	 **/
	private RefreshTokenEntity createRefreshToken(String userName)
	{
		UserEntity user = userService.getUserByName(userName);
		Optional<RefreshTokenEntity> oldRefreshToken = refreshTokenRepository.findByUser(user);

		oldRefreshToken.ifPresent(refreshTokenRepository::delete);

		RefreshTokenEntity refreshToken = RefreshTokenEntity.builder()
															.user(user)
															.token(JwtHelper.generateRefreshToken())
															.expiryDate(Instant.now().plusSeconds(600))
															.build();
		return refreshTokenRepository.save(refreshToken);
	}
}
