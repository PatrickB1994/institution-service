package com.mdsl.institutionservice.service.implementation;

import com.mdsl.institutionservice.dto.BaseResponse;
import com.mdsl.institutionservice.dto.InstitutionDto;
import com.mdsl.institutionservice.entity.InstitutionEntity;
import com.mdsl.institutionservice.enums.ResponseStatus;
import com.mdsl.institutionservice.exception.EntityNotFoundException;
import com.mdsl.institutionservice.exception.InvalidRequestException;
import com.mdsl.institutionservice.repository.InstitutionRepository;
import com.mdsl.institutionservice.shared.Validation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InstitutionImplTest
{

	@Mock
	private Validation validation;

	@Mock
	private InstitutionRepository institutionRepository;

	@InjectMocks
	private InstitutionImpl institutionService;

	@BeforeEach
	public void setUp()
	{
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testAddOrUpdateInstitution_AddNewInstitution()
	{
		// Arrange
		InstitutionDto institutionDto = new InstitutionDto();
		institutionDto.setInstitutionCode(12345L);
		institutionDto.setInstitutionName("name");
		institutionDto.setInstitutionStatus(0L);

		when(institutionRepository.findByInstitutionCode(anyLong())).thenReturn(Optional.empty());
		when(institutionRepository.save(any(InstitutionEntity.class))).thenReturn(new InstitutionEntity());

		// Act
		BaseResponse<InstitutionEntity> response = institutionService.addOrUpdateInstitution(institutionDto);

		// Assert
		assertNotNull(response.getEntity());
		assertEquals(ResponseStatus.SUCCESS.getStatus(), response.getDeveloperMessage());
		assertEquals("Institution added successfully", response.getMessage());
	}

	@Test
	public void testAddOrUpdateInstitution_UpdateExistingInstitution()
	{
		// Arrange
		Long institutionId = 1L;
		InstitutionDto institutionDto = new InstitutionDto();
		institutionDto.setId(institutionId);

		InstitutionEntity existingInstitution = new InstitutionEntity();
		existingInstitution.setId(institutionId);
		existingInstitution.setInstitutionCode(12345L);
		existingInstitution.setInstitutionName("name");
		existingInstitution.setInstitutionStatus(0L);

		when(institutionRepository.findById(anyLong())).thenReturn(Optional.of(existingInstitution));
		when(institutionRepository.save(any(InstitutionEntity.class))).thenReturn(existingInstitution);

		// Act
		BaseResponse<InstitutionEntity> response = institutionService.addOrUpdateInstitution(institutionDto);

		// Assert
		assertNotNull(response.getEntity());
		assertEquals(ResponseStatus.SUCCESS.getStatus(), response.getDeveloperMessage());
		assertEquals("Institution updated successfully", response.getMessage());
	}


	@Test
	public void testAddOrUpdateInstitution_InvalidRequestException() {
		// Arrange
		InstitutionDto institutionDto = new InstitutionDto();
		institutionDto.setInstitutionCode(12345L);

		when(institutionRepository.findByInstitutionCode(anyLong())).thenReturn(Optional.of(new InstitutionEntity()));

		// Act & Assert
		assertThrows(InvalidRequestException.class, () -> institutionService.addOrUpdateInstitution(institutionDto));
	}

	@Test
	public void testGetInstitution()
	{
		// Arrange
		Long institutionId = 1L;
		Pageable pageable = Pageable.unpaged();
		InstitutionEntity institutionEntity = new InstitutionEntity();
		institutionEntity.setId(institutionId);

		when(institutionRepository.findByIdOrStatusOrAll(eq(institutionId), eq(null), eq(pageable))).thenReturn(Page.empty());

		// Act
		BaseResponse<Page<InstitutionEntity>> response = institutionService.getInstitution(institutionId, null, pageable);

		// Assert
		assertEquals(ResponseStatus.SUCCESS.getStatus(), response.getDeveloperMessage());
		assertEquals("Institutions retrieved successfully", response.getMessage());
		assertNotNull(response.getEntity());
		assertTrue(response.getEntity().isEmpty());
	}

	@Test
	public void testDeleteInstitution()
	{
		// Arrange
		Long institutionId = 1L;
		when(institutionRepository.findById(eq(institutionId))).thenReturn(Optional.of(new InstitutionEntity()));

		// Act
		BaseResponse<?> response = institutionService.deleteInstitution(institutionId);

		// Assert
		assertEquals(ResponseStatus.SUCCESS.getStatus(), response.getDeveloperMessage());
		assertEquals("Institution deleted successfully", response.getMessage());
	}

	@Test
	public void testDeleteInstitution_EntityNotFoundException()
	{
		// Arrange
		Long institutionId = 1L;
		when(institutionRepository.findById(eq(institutionId))).thenReturn(Optional.empty());

		// Act & Assert
		assertThrows(EntityNotFoundException.class, () -> institutionService.deleteInstitution(institutionId));
	}
}
