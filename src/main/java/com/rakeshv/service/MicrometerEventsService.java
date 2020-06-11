package com.rakeshv.service;

import com.google.common.util.concurrent.AtomicDouble;
import com.rakeshv.models.ApiCount;
import com.rakeshv.models.micrometer.EventType;
import com.rakeshv.repositories.ApiCountRepository;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@Slf4j
public class MicrometerEventsService {
    ApiCountRepository apiCountRepository;

    AtomicDouble apiCountPerMinute = new AtomicDouble(0);
    AtomicDouble totalApiCount = new AtomicDouble(0);
    MicrometerEventsService(MeterRegistry meterRegistry,
                            ApiCountRepository apiCountRepository) {
        log.info("Registering stats for prometheus");
        Gauge.builder(EventType.API_COUNT_PER_MINUTE.getCounterName(), this, this::getApiCountPerMinute)
                .description(EventType.API_COUNT_PER_MINUTE.getDescription())
                .register(meterRegistry);
        Gauge.builder(EventType.TOTAL_API_COUNT.getCounterName(), this, this::getTotalApiCount)
                .description(EventType.TOTAL_API_COUNT.getDescription())
                .register(meterRegistry);

        this.apiCountRepository = apiCountRepository;
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

    @PostConstruct
    public void populate() {
        List<ApiCount> apiCountList = apiCountRepository.findAll();
        long sum = apiCountList.stream()
                .map(ApiCount::getCount)
                .reduce(0L, Long::sum);
        setTotalApiCount((double)sum);
    }
}
