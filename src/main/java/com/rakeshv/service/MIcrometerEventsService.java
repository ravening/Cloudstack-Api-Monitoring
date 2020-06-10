package com.rakeshv.service;

import com.google.common.util.concurrent.AtomicDouble;
import com.rakeshv.models.micrometer.EventType;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MicrometerEventsService {
    AtomicDouble apiCountPerMinute = new AtomicDouble(0);
    AtomicDouble totalApiCount = new AtomicDouble(0);
    MicrometerEventsService(MeterRegistry meterRegistry) {
        log.info("Registering stats for prometheus");
        Gauge.builder(EventType.API_COUNT_PER_MINUTE.getCounterName(), this, this::getApiCountPerMinute)
                .description(EventType.API_COUNT_PER_MINUTE.getDescription())
                .register(meterRegistry);
        Gauge.builder(EventType.TOTAL_API_COUNT.getCounterName(), this, this::getTotalApiCount)
                .description(EventType.TOTAL_API_COUNT.getDescription())
                .register(meterRegistry);

    }

    public double getApiCountPerMinute(MicrometerEventsService micrometerEventsService) {
        return micrometerEventsService.apiCountPerMinute.get();
    }

    public double getTotalApiCount(MicrometerEventsService micrometerEventsService) {
        return micrometerEventsService.totalApiCount.get();
    }

    public void setApiCountPerMinute(double value) {
        log.debug("Api per minute is {}", value);
        apiCountPerMinute.set(value);
        setTotalApiCount(value);
    }

    private void setTotalApiCount(double value) {
        totalApiCount.getAndAdd(value);
        log.debug("Total api count is {}", totalApiCount.get());
    }
}
