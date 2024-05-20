package com.mdsl.institutionservice.repository;

import com.mdsl.institutionservice.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long>
{
	@Query("SELECT r FROM RoleEntity r WHERE (:roleName IS NULL OR LOWER(r.name) LIKE LOWER(CONCAT('%', :roleName, '%')))")
	Page<RoleEntity> findAllOrByName(String roleName, Pageable pageable);

	Optional<RoleEntity> findByName(String roleName);
}
