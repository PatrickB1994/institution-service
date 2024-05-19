package com.mdsl.institutionservice.repository;

import com.mdsl.institutionservice.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long>
{
	Optional<RoleEntity> findByName(String roleName);
}
