package com.mdsl.institutionservice.repository;

import com.mdsl.institutionservice.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>
{
	Optional<UserEntity> findByName(String name);

	@Query("SELECT u FROM UserEntity u WHERE CASE WHEN :id IS NOT NULL AND :name IS NOT NULL THEN u.id = :id AND u.name = :name " +
			"WHEN :id IS NOT NULL THEN u.id = :id WHEN :name IS NOT NULL THEN u.name = :name ELSE true END ")
	Page<UserEntity> findByIdOrNameOrAll(Long id, String name, Pageable pageable);
}
