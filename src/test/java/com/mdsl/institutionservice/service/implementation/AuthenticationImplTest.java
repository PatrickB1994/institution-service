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
import com.mdsl.institutionservice.service.CustomMetricService;
import com.mdsl.institutionservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthenticationImplTest
{

	@Mock
	private AuthenticationManager authenticationManager;

	@Mock
	private RefreshTokenRepository refreshTokenRepository;

	@Mock
	private UserService userService;

	@Mock
	private CustomMetricService customMetricService;

	@InjectMocks
	private AuthenticationImpl authenticationService;

	@BeforeEach
	public void setUp()
	{
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testLogin_Success()
	{
		// Arrange
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUserName("user");
		loginRequest.setPassword("password");

		UserEntity userEntity = new UserEntity();
		userEntity.setName("user");

		when(userService.getUserByName(anyString())).thenReturn(userEntity);
		when(authenticationManager.authenticate(any())).thenReturn(null);
		when(refreshTokenRepository.findByUser(any())).thenReturn(Optional.empty());
		when(refreshTokenRepository.save(any(RefreshTokenEntity.class))).thenReturn(new RefreshTokenEntity());

		// Act
		BaseResponse<LoginResponse> response = authenticationService.login(loginRequest);

		// Assert
		assertNotNull(response.getEntity());
		assertNotNull(response.getEntity().getToken());
		assertEquals(ResponseStatus.SUCCESS.getStatus(), response.getMessage());
	}

	@Test
	public void testRefreshToken_Success()
	{
		// Arrange
		String refreshToken = "refreshToken";
		RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
		refreshTokenRequest.setRefreshToken(refreshToken);

		UserEntity userEntity = new UserEntity();
		userEntity.setName("user");

		RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity();
		refreshTokenEntity.setUser(userEntity);
		refreshTokenEntity.setExpiryDate(Instant.now().plusSeconds(600));

		when(refreshTokenRepository.findByToken(anyString())).thenReturn(Optional.of(refreshTokenEntity));

		// Act
		BaseResponse<LoginResponse> response = authenticationService.refreshToken(refreshTokenRequest);

		// Assert
		assertNotNull(response.getEntity());
		assertNotNull(response.getEntity().getToken());
		assertEquals(refreshToken, response.getEntity().getRefreshToken());
		assertEquals(ResponseStatus.SUCCESS.getStatus(), response.getMessage());
	}

	@Test
	public void testRefreshToken_ExpiredToken()
	{
		// Arrange
		String refreshToken = "expiredToken";
		RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
		refreshTokenRequest.setRefreshToken(refreshToken);

		RefreshTokenEntity expiredTokenEntity = new RefreshTokenEntity();
		expiredTokenEntity.setExpiryDate(Instant.now().minusSeconds(600));

		when(refreshTokenRepository.findByToken(anyString())).thenReturn(Optional.of(expiredTokenEntity));
		doNothing().when(refreshTokenRepository).delete(any());

		// Act & Assert
		assertThrows(TokenExpiredException.class, () -> authenticationService.refreshToken(refreshTokenRequest));
	}

	@Test
	public void testRefreshToken_TokenNotFound()
	{
		// Arrange
		String refreshToken = "nonExistentToken";
		RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
		refreshTokenRequest.setRefreshToken(refreshToken);

		when(refreshTokenRepository.findByToken(anyString())).thenReturn(Optional.empty());

		// Act & Assert
		assertThrows(AccessDeniedException.class, () -> authenticationService.refreshToken(refreshTokenRequest));
	}
}
