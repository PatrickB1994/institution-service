package com.mdsl.institutionservice.repository;

import com.mdsl.institutionservice.entity.RefreshTokenEntity;
import com.mdsl.institutionservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long>
{
	Optional<RefreshTokenEntity> findByToken(String token);

	Optional<RefreshTokenEntity> findByUser(UserEntity user);
}
