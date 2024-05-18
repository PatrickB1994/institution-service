package com.mdsl.institutionservice.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InstitutionDtoTest {

	@Test
	public void testGettersAndSetters() {
		// Arrange
		InstitutionDto institutionDto = new InstitutionDto();
		Long id = 1L;
		Long institutionCode = 12345L;
		String institutionName = "Test Institution";
		Long institutionStatus = 1L;

		// Act
		institutionDto.setId(id);
		institutionDto.setInstitutionCode(institutionCode);
		institutionDto.setInstitutionName(institutionName);
		institutionDto.setInstitutionStatus(institutionStatus);

		// Assert
		assertEquals(id, institutionDto.getId());
		assertEquals(institutionCode, institutionDto.getInstitutionCode());
		assertEquals(institutionName, institutionDto.getInstitutionName());
		assertEquals(institutionStatus, institutionDto.getInstitutionStatus());
	}

	@Test
	public void testDefaultConstructor() {
		// Arrange & Act
		InstitutionDto institutionDto = new InstitutionDto();

		// Assert
		assertNull(institutionDto.getId());
		assertNull(institutionDto.getInstitutionCode());
		assertNull(institutionDto.getInstitutionName());
		assertNull(institutionDto.getInstitutionStatus());
	}

	@Test
	public void testAllArgsConstructor() {
		// Arrange
		Long id = 1L;
		Long institutionCode = 12345L;
		String institutionName = "Test Institution";
		Long institutionStatus = 1L;

		// Act
		InstitutionDto institutionDto = new InstitutionDto();
		institutionDto.setId(id);
		institutionDto.setInstitutionCode(institutionCode);
		institutionDto.setInstitutionName(institutionName);
		institutionDto.setInstitutionStatus(institutionStatus);

		// Assert
		assertEquals(id, institutionDto.getId());
		assertEquals(institutionCode, institutionDto.getInstitutionCode());
		assertEquals(institutionName, institutionDto.getInstitutionName());
		assertEquals(institutionStatus, institutionDto.getInstitutionStatus());
	}
}
