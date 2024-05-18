package com.mdsl.institutionservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstitutionDto
{
	private Long id;
	private Long institutionCode;
	private String institutionName;
	private Long institutionStatus;
}
