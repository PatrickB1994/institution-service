package com.mdsl.institutionservice.shared;

import com.mdsl.institutionservice.dto.InstitutionDto;
import com.mdsl.institutionservice.dto.UserDto;
import com.mdsl.institutionservice.exception.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Getter
@Setter
@Component
public class Validation
{

	@Value("${password.pattern}")
	private String passwordPattern;

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

	public void validateAddOrUpdateUser(UserDto userDto)
	{
		boolean isUpdate = userDto.getId() != null && userDto.getId() != 0;

		if(!isUpdate && userDto.getUserName() == null)
		{
			throw new InvalidUserNameException();
		}
		if(!isUpdate && (userDto.getPassword() == null || !isValidPassword(userDto.getPassword())))
		{
			throw new InvalidPasswordException(
					"Password should have at least one digit, at least one lowercase letter, at least one uppercase letter, at least one special character and length between 8 and 20 characters");
		}
		if(!isUpdate && userDto.getRoles().isEmpty())
		{
			throw new InvalidRoleException();
		}
	}

	public boolean isValidPassword(String password)
	{
		return Pattern.compile(passwordPattern).matcher(password).matches();
	}

}
