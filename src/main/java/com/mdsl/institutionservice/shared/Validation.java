package com.mdsl.institutionservice.shared;

import com.mdsl.institutionservice.dto.InstitutionDto;
import com.mdsl.institutionservice.exception.EntityNotFoundException;
import com.mdsl.institutionservice.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Validation
{

	public void validateAddUpdateInstitution(InstitutionDto institutionDto)
	{
		boolean isUpdate = institutionDto.getId() != null && institutionDto.getId() != 0;
		Long code = institutionDto.getInstitutionCode();
		String name = institutionDto.getInstitutionName();
		Long status = institutionDto.getInstitutionStatus();

		if(!isUpdate && code == null)
		{
			throw new ValidationException("Institution Code Cannot be null.");
		}
		if(code != null && !String.valueOf(code).matches("\\d{1,5}"))
		{
			throw new ValidationException("Institution Code must be numeric with max length 5.");
		}
		if(!isUpdate && name == null)
		{
			throw new ValidationException("Institution Name Cannot be null.");
		}
		if(name != null && !name.matches("^\\d{1,50}$"))
		{
			throw new ValidationException("Institution Name must be numeric with max length 50.");
		}
		if(!isUpdate && status == null)
		{
			throw new ValidationException("Institution Status Cannot be null.");
		}
		if(status != null && status != 0 && status != 1)
		{
			throw new ValidationException("Institution Status should be 0 or 1.");
		}
	}

	public void validateDeleteInstitution(Long id)
	{
		if(id == null)
		{
			throw new EntityNotFoundException();
		}
	}

}
