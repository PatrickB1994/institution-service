package com.mdsl.institutionservice.shared;

import com.mdsl.institutionservice.dto.InstitutionDto;
import com.mdsl.institutionservice.exception.EntityNotFoundException;
import com.mdsl.institutionservice.exception.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidationTest
{

	private Validation validation;

	@BeforeEach
	public void setUp()
	{
		validation = new Validation();
	}

	@Test
	public void testValidateAddUpdateInstitution_ValidData()
	{
		// Arrange
		InstitutionDto institutionDto = new InstitutionDto();
		institutionDto.setInstitutionCode(12345L);
		institutionDto.setInstitutionName("124312423");
		institutionDto.setInstitutionStatus(1L);

		// Act & Assert
		assertDoesNotThrow(() -> validation.validateAddUpdateInstitution(institutionDto));
	}

	@Test
	public void testValidateAddUpdateInstitution_NullInstitutionCode()
	{
		// Arrange
		InstitutionDto institutionDto = new InstitutionDto();
		institutionDto.setInstitutionName("32424234234");
		institutionDto.setInstitutionStatus(1L);

		// Act & Assert
		ValidationException exception = assertThrows(ValidationException.class, () -> validation.validateAddUpdateInstitution(institutionDto));
		assertEquals("Institution Code Cannot be null.", exception.getMessage());
	}

	@Test
	public void testValidateAddUpdateInstitution_InvalidInstitutionCode()
	{
		// Arrange
		InstitutionDto institutionDto = new InstitutionDto();
		institutionDto.setInstitutionCode(123456L); // Invalid: more than 5 digits
		institutionDto.setInstitutionName("234234234234");
		institutionDto.setInstitutionStatus(1L);

		// Act & Assert
		ValidationException exception = assertThrows(ValidationException.class, () -> validation.validateAddUpdateInstitution(institutionDto));
		assertEquals("Institution Code must be numeric with max length 5.", exception.getMessage());
	}

	@Test
	public void testValidateAddUpdateInstitution_NullInstitutionName()
	{
		// Arrange
		InstitutionDto institutionDto = new InstitutionDto();
		institutionDto.setInstitutionCode(12345L);
		institutionDto.setInstitutionStatus(1L);

		// Act & Assert
		ValidationException exception = assertThrows(ValidationException.class, () -> validation.validateAddUpdateInstitution(institutionDto));
		assertEquals("Institution Name Cannot be null.", exception.getMessage());
	}

	@Test
	public void testValidateAddUpdateInstitution_InvalidInstitutionName()
	{
		// Arrange
		InstitutionDto institutionDto = new InstitutionDto();
		institutionDto.setInstitutionCode(12345L);
		institutionDto.setInstitutionName("Invalid Name 123456789012345678901234567890123456789012345678901"); // Invalid: more than 50 characters
		institutionDto.setInstitutionStatus(1L);

		// Act & Assert
		ValidationException exception = assertThrows(ValidationException.class, () -> validation.validateAddUpdateInstitution(institutionDto));
		assertEquals("Institution Name must be numeric with max length 50.", exception.getMessage());
	}

	@Test
	public void testValidateAddUpdateInstitution_NullInstitutionStatus()
	{
		// Arrange
		InstitutionDto institutionDto = new InstitutionDto();
		institutionDto.setInstitutionCode(12345L);
		institutionDto.setInstitutionName("23423423423423");

		// Act & Assert
		ValidationException exception = assertThrows(ValidationException.class, () -> validation.validateAddUpdateInstitution(institutionDto));
		assertEquals("Institution Status Cannot be null.", exception.getMessage());
	}

	@Test
	public void testValidateAddUpdateInstitution_InvalidInstitutionStatus()
	{
		// Arrange
		InstitutionDto institutionDto = new InstitutionDto();
		institutionDto.setInstitutionCode(12345L);
		institutionDto.setInstitutionName("3453453453453");
		institutionDto.setInstitutionStatus(2L); // Invalid: not 0 or 1

		// Act & Assert
		ValidationException exception = assertThrows(ValidationException.class, () -> validation.validateAddUpdateInstitution(institutionDto));
		assertEquals("Institution Status should be 0 or 1.", exception.getMessage());
	}

	@Test
	public void testValidateDeleteInstitution_NullId()
	{
		// Act & Assert
		assertThrows(EntityNotFoundException.class, () -> validation.validateDeleteInstitution(null));
	}

	@Test
	public void testValidateDeleteInstitution_ValidId()
	{
		// Act & Assert
		assertDoesNotThrow(() -> validation.validateDeleteInstitution(1L));
	}
}
