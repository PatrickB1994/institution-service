package com.mdsl.institutionservice.service;

import com.mdsl.institutionservice.dto.BaseResponse;
import com.mdsl.institutionservice.dto.UserDto;
import com.mdsl.institutionservice.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService
{
	UserEntity getUserByName(String userName);

	BaseResponse<Page<UserEntity>> getUser(Long id, String name, Pageable pageable);

	BaseResponse<UserEntity> addOrUpdateUser(UserDto userDto);

	BaseResponse<?> deleteUser(Long userId, String currentUserName);
}
