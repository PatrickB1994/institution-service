package com.mdsl.institutionservice.controller;

import com.mdsl.institutionservice.dto.BaseResponse;
import com.mdsl.institutionservice.dto.InstitutionDto;
import com.mdsl.institutionservice.entity.InstitutionEntity;
import com.mdsl.institutionservice.service.InstitutionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/institution")
@RequiredArgsConstructor
public class InstitutionController
{

	private final InstitutionService institutionService;

	@Operation(summary = "Add Or Update Institution", security = @SecurityRequirement(name = "bearerAuth"))
	@PostMapping()
	public BaseResponse<InstitutionEntity> addOrUpdateInstitution(HttpServletRequest request, @RequestBody InstitutionDto institutionDto)
	{
		return institutionService.addOrUpdateInstitution(institutionDto);
	}

	@Operation(summary = "Get institutions", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping()
	public BaseResponse<Page<InstitutionEntity>> getInstitution(HttpServletRequest request, @RequestParam(required = false) Long id,
			@RequestParam(required = false) Long status, Pageable pageable)
	{
		return institutionService.getInstitution(id, status, pageable);
	}

	@Operation(summary = "Delete Institution", security = @SecurityRequirement(name = "bearerAuth"))
	@DeleteMapping()
	public BaseResponse<?> deleteInstitution(HttpServletRequest request, @RequestParam(required = true) Long id)
	{
		return institutionService.deleteInstitution(id);
	}

}
