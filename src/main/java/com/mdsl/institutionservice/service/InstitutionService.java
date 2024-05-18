package com.mdsl.institutionservice.service;

import com.mdsl.institutionservice.dto.BaseResponse;
import com.mdsl.institutionservice.dto.InstitutionDto;
import com.mdsl.institutionservice.entity.InstitutionEntity;

import java.util.List;

public interface InstitutionService
{
	BaseResponse<InstitutionEntity> addOrUpdateInstitution(InstitutionDto institutionDto);

	BaseResponse<List<InstitutionEntity>> getInstitution(Long id, Long status);

	BaseResponse<?> deleteInstitution(Long id);
}
