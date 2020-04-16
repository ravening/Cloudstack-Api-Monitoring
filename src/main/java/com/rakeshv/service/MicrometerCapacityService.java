package com.rakeshv.service;

import com.rakeshv.models.micrometer.CapacityType;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class MicrometerCapacityService {
    @Autowired
    CloudstackCapacityService cloudstackCapacityService;

    Long capacityAllocated = 0L;
    Long capacityTotal = 0L;
    Long capacityUsed = 0L;
    HashMap<CapacityType, Long> gaugeHashMap = new HashMap<>();

    MicrometerCapacityService(MeterRegistry meterRegistry) {

    }
}
