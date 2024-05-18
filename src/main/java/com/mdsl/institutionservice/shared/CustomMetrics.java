package com.mdsl.institutionservice.shared;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomMetrics
{

	private final MeterRegistry meterRegistry;
	private Counter loginCounter;
	private Counter refreshCounter;

	@PostConstruct
	public void init()
	{
		loginCounter = meterRegistry.counter("login_count");
		refreshCounter = meterRegistry.counter("refresh_count");
	}

	public void incrementLoginCounter()
	{
		loginCounter.increment();
	}

	public void incrementRefreshCounter()
	{
		refreshCounter.increment();
	}
}
