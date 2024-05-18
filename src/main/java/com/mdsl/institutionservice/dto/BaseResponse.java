package com.mdsl.institutionservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Accessors(chain = true)
public class BaseResponse<T>
{
	@JsonIgnore
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	private T entity;
	private String message;
	private String developerMessage;
	private int statusCode;
	private String timestamp = LocalDateTime.now().format(formatter);
}
