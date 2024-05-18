package com.mdsl.institutionservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "institution")
public class InstitutionEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "institution_code", nullable = false, unique = true, length = 5)
	private Long institutionCode;
	@Column(name = "institution_name", nullable = false, length = 50)
	private String institutionName;
	@Column(name = "institution_status", nullable = false)
	private Long institutionStatus;
}
