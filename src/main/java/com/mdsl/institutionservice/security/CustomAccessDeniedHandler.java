package com.mdsl.institutionservice.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.time.LocalDateTime;

@Configuration
public class CustomAccessDeniedHandler implements AccessDeniedHandler
{
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
			throws IOException, ServletException
	{
		final LocalDateTime now = LocalDateTime.now();
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.getOutputStream()
				.println(
						"{ \"entity\": [], \"message\": \"Unauthorized request\", \"developerMessage\": \"Unauthorized request\", \"statusCode\": 4002, \"timestamp\": \"" +
								now + "\" }");
	}
}
