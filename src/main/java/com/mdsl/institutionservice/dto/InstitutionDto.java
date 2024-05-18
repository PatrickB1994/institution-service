package com.mdsl.institutionservice.dto;

import lombok.Data;

@Data
public class InstitutionDto
{
	private Long id;
	private Long institutionCode;
	private String institutionName;
	private Long institutionStatus;
}
