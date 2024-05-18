package com.mdsl.institutionservice.service.implementation;

import com.mdsl.institutionservice.service.CustomMetricService;
import com.mdsl.institutionservice.shared.CustomMetrics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomMetricImpl implements CustomMetricService
{
	private final CustomMetrics customMetrics;

	public void loginSuccess()
	{
		customMetrics.incrementLoginCounter();
	}

	public void refreshSuccess()
	{
		customMetrics.incrementRefreshCounter();
	}

}