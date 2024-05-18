package com.mdsl.institutionservice.exception;

import com.mdsl.institutionservice.dto.BaseResponse;
import com.mdsl.institutionservice.enums.ResponseStatus;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static com.mdsl.institutionservice.enums.ExceptionCode.*;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler
{

	@ExceptionHandler(value = { HttpRequestMethodNotSupportedException.class })
	public ResponseEntity<BaseResponse<?>> handleMethodNotSupported(HttpRequestMethodNotSupportedException exception)
	{
		BaseResponse<?> response = new BaseResponse<>();
		response.setDeveloperMessage(exception.getMessage())
				.setMessage(ResponseStatus.FAILED.getStatus())
				.setStatusCode(METHOD_NOT_SUPPORTED_EXCEPTION.getCode());
		return ResponseEntity.badRequest().body(response);
	}

	@ExceptionHandler(value = { org.springframework.security.access.AccessDeniedException.class })
	public ResponseEntity<BaseResponse<?>> handleAccessDeniedException(AccessDeniedException exception)
	{
		BaseResponse<?> response = new BaseResponse<>();
		response.setDeveloperMessage(exception.getMessage())
				.setMessage(AccessDeniedException.getMessage())
				.setStatusCode(AccessDeniedException.getCode());
		return ResponseEntity.badRequest().body(response);
	}

	@ExceptionHandler(value = { MethodArgumentTypeMismatchException.class })
	public ResponseEntity<BaseResponse<?>> handleArgumentsTypeMismatch(MethodArgumentTypeMismatchException exception)
	{
		BaseResponse<?> response = new BaseResponse<>();
		response.setDeveloperMessage(exception.getMessage()).setMessage(ResponseStatus.FAILED.getStatus());
		return ResponseEntity.badRequest().body(response);
	}

	@ExceptionHandler({ ValidationException.class })
	public ResponseEntity<BaseResponse<?>> handleBaseException(ValidationException validationException)
	{
		BaseResponse<?> response = new BaseResponse<>();
		response.setMessage(validationException.getMessage()).setStatusCode(VALIDATION_EXCEPTION.getCode());
		return ResponseEntity.badRequest().body(response);
	}

	@ExceptionHandler({ BaseException.class })
	public ResponseEntity<BaseResponse<?>> handleBaseException(BaseException baseException)
	{
		BaseResponse<?> response = new BaseResponse<>();
		response.setMessage(baseException.getMessage()).setStatusCode(GENERAL_EXCEPTION.getCode());
		return ResponseEntity.badRequest().body(response);
	}

	@ExceptionHandler({ RuntimeException.class, JsonParseException.class })
	public ResponseEntity<BaseResponse<?>> handleRuntimeException(Exception e)
	{
		log.error("An unexpected error occurred: {}", e.getMessage(), e);
		BaseResponse<?> response = new BaseResponse<>();
		response.setDeveloperMessage(e.getMessage()).setMessage(ResponseStatus.FAILED.getStatus()).setStatusCode(GENERAL_EXCEPTION.getCode());
		return ResponseEntity.badRequest().body(response);
	}
}
