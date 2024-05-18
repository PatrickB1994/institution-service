package com.mdsl.institutionservice.service.implementation;

import com.mdsl.institutionservice.dto.BaseResponse;
import com.mdsl.institutionservice.dto.InstitutionDto;
import com.mdsl.institutionservice.entity.InstitutionEntity;
import com.mdsl.institutionservice.enums.ResponseStatus;
import com.mdsl.institutionservice.exception.EntityNotFoundException;
import com.mdsl.institutionservice.exception.InvalidRequestException;
import com.mdsl.institutionservice.repository.InstitutionRepository;
import com.mdsl.institutionservice.service.InstitutionService;
import com.mdsl.institutionservice.shared.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstitutionImpl implements InstitutionService
{
	private final Validation validation;
	private final InstitutionRepository institutionRepository;

	/**
	 * This method is responsible for creating and updating institutions
	 *
	 * @param institutionDto the DTO containing institution information
	 * @return BaseResponse<InstitutionEntity> containing the saved or updated entity
	 **/
	@Override
	public BaseResponse<InstitutionEntity> addOrUpdateInstitution(InstitutionDto institutionDto)
	{
		Long institutionId = institutionDto.getId();
		boolean isUpdate = institutionId != null && institutionId != 0;

		// Validate institution data
		validation.validateAddUpdateInstitution(institutionDto);

		// Initialize response and entity
		BaseResponse<InstitutionEntity> response = new BaseResponse<>();
		InstitutionEntity institutionEntity = new InstitutionEntity();

		// Handle update case
		if(isUpdate)
		{
			institutionEntity = institutionRepository.findById(institutionId)
													 .orElseThrow(() -> new EntityNotFoundException("Institution ID: " + institutionId));
		}
		else
		{
			if(institutionRepository.findByInstitutionCode(institutionDto.getInstitutionCode()).isPresent())
			{
				throw new InvalidRequestException("Institution code already exist");
			}
		}

		// Update fields with non-null values from DTO
		updateInstitutionFields(institutionDto, institutionEntity);

		// Save entity to repository
		institutionEntity = institutionRepository.save(institutionEntity);

		response.setEntity(institutionEntity)
				.setDeveloperMessage(ResponseStatus.SUCCESS.getStatus())
				.setMessage(isUpdate ? "Institution updated successfully" : "Institution added successfully");
		return response;
	}

	/**
	 * This method is used to update the non-null fields
	 *
	 * @param dto    the DTO containing institution information
	 * @param entity the entity which will be updated
	 **/
	private void updateInstitutionFields(InstitutionDto dto, InstitutionEntity entity)
	{
		if(dto.getInstitutionCode() != null)
		{
			entity.setInstitutionCode(dto.getInstitutionCode());
		}
		if(dto.getInstitutionName() != null)
		{
			entity.setInstitutionName(dto.getInstitutionName());
		}
		if(dto.getInstitutionStatus() != null)
		{
			entity.setInstitutionStatus(dto.getInstitutionStatus());
		}
	}

	/**
	 * This method is responsible for retrieving institutions by ID, status or all
	 *
	 * @param id     the institution ID to filter by
	 * @param status the  institution status to filter by
	 * @return BaseResponse<List < InstitutionEntity>> containing the list of institution entities
	 **/
	@Override
	public BaseResponse<Page<InstitutionEntity>> getInstitution(Long id, Long status, Pageable pageable)
	{
		BaseResponse<Page<InstitutionEntity>> response = new BaseResponse<>();

		Page<InstitutionEntity> institutions = institutionRepository.findByIdOrStatusOrAll(id, status, pageable);

		response.setEntity(institutions).setDeveloperMessage(ResponseStatus.SUCCESS.getStatus()).setMessage("Institutions retrieved successfully");
		return response;
	}

	/**
	 * This method is responsible for deleting institutions by ID
	 *
	 * @param id the institution ID to be deleted
	 * @return BaseResponse
	 **/
	@Override
	public BaseResponse<?> deleteInstitution(Long id)
	{
		BaseResponse<List<InstitutionEntity>> response = new BaseResponse<>();

		validation.validateDeleteInstitution(id);

		institutionRepository.findById(id).orElseThrow(EntityNotFoundException::new);

		institutionRepository.deleteById(id);

		response.setDeveloperMessage(ResponseStatus.SUCCESS.getStatus()).setMessage("Institution deleted successfully");
		return response;
	}
}
