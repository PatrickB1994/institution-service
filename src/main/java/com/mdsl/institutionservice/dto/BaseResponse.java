package com.mdsl.institutionservice.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class BaseResponse<T>
{
	private T entity;
	private String message;
	private String developerMessage;
	private int statusCode;
	private LocalDateTime timestamp = LocalDateTime.now();
}
