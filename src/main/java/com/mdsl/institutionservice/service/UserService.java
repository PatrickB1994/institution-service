package com.mdsl.institutionservice.service;

import com.mdsl.institutionservice.entity.UserEntity;

public interface UserService
{
	UserEntity getUserByName(String userName);
}
