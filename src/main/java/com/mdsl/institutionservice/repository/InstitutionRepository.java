package com.mdsl.institutionservice.repository;

import com.mdsl.institutionservice.entity.InstitutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstitutionRepository extends JpaRepository<InstitutionEntity, Long>
{
	@Query("SELECT i FROM InstitutionEntity i " +
			"WHERE CASE " +
			"WHEN :id IS NOT NULL AND :status IS NOT NULL THEN i.id = :id AND i.institutionStatus = :status " +
			"WHEN :id IS NOT NULL THEN i.id = :id " +
			"WHEN :status IS NOT NULL THEN i.institutionStatus = :status " +
			"ELSE true END ")
	List<InstitutionEntity> findByIdOrStatusOrAll(Long id, Long status);
}
