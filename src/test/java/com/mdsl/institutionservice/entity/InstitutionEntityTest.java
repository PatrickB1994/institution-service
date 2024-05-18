package com.mdsl.institutionservice.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InstitutionEntityTest
{

	@Test
	public void testGetterAndSetterMethods()
	{
		// Arrange
		Long id = 1L;
		Long institutionCode = 12345L;
		String institutionName = "Test Institution";
		Long institutionStatus = 1L;

		InstitutionEntity institution = new InstitutionEntity();

		// Act
		institution.setId(id);
		institution.setInstitutionCode(institutionCode);
		institution.setInstitutionName(institutionName);
		institution.setInstitutionStatus(institutionStatus);

		// Assert
		assertEquals(id, institution.getId());
		assertEquals(institutionCode, institution.getInstitutionCode());
		assertEquals(institutionName, institution.getInstitutionName());
		assertEquals(institutionStatus, institution.getInstitutionStatus());
	}

	@Test
	public void testBuilder()
	{
		// Arrange
		Long id = 1L;
		Long institutionCode = 12345L;
		String institutionName = "Test Institution";
		Long institutionStatus = 1L;

		// Act
		InstitutionEntity institution = InstitutionEntity.builder()
														 .id(id)
														 .institutionCode(institutionCode)
														 .institutionName(institutionName)
														 .institutionStatus(institutionStatus)
														 .build();

		// Assert
		assertNotNull(institution);
		assertEquals(id, institution.getId());
		assertEquals(institutionCode, institution.getInstitutionCode());
		assertEquals(institutionName, institution.getInstitutionName());
		assertEquals(institutionStatus, institution.getInstitutionStatus());
	}

	@Test
	public void testAllArgsConstructor()
	{
		// Arrange
		Long id = 1L;
		Long institutionCode = 12345L;
		String institutionName = "Test Institution";
		Long institutionStatus = 1L;

		// Act
		InstitutionEntity institution = new InstitutionEntity(id, institutionCode, institutionName, institutionStatus);

		// Assert
		assertNotNull(institution);
		assertEquals(id, institution.getId());
		assertEquals(institutionCode, institution.getInstitutionCode());
		assertEquals(institutionName, institution.getInstitutionName());
		assertEquals(institutionStatus, institution.getInstitutionStatus());
	}
}
