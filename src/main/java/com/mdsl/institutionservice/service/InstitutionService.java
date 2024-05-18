package com.mdsl.institutionservice.service;

import com.mdsl.institutionservice.dto.BaseResponse;
import com.mdsl.institutionservice.dto.InstitutionDto;
import com.mdsl.institutionservice.entity.InstitutionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InstitutionService
{
	BaseResponse<InstitutionEntity> addOrUpdateInstitution(InstitutionDto institutionDto);

	BaseResponse<Page<InstitutionEntity>> getInstitution(Long id, Long status, Pageable pageable);

	BaseResponse<?> deleteInstitution(Long id);
}
