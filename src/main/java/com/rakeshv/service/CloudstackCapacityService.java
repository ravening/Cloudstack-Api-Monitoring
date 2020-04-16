package com.rakeshv.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakeshv.models.cloudstackresponse.Capacity;
import com.rakeshv.models.cloudstackresponse.CapacityResponse;
import com.rakeshv.models.cloudstackresponse.ListCapacityResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CloudstackCapacityService {
    @Autowired
    private CommandBuilderService commandBuilderService;

    //@Scheduled(fixedRate = 60000)
    public List<Capacity> listCapacity() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Capacity> allCapacities = new ArrayList<>();
        Map<String, String> resultMap = commandBuilderService.executeOnAllPlatforms("listCapacity", null);
        for (String key : resultMap.keySet()) {
            ListCapacityResponse listCapacityResponse = mapper.readValue(resultMap.get(key), ListCapacityResponse.class);
            CapacityResponse capacityResponse = listCapacityResponse.getListcapacityresponse();
            List<Capacity> capacityList = capacityResponse.getCapacity();
            capacityList.forEach(capacity -> {
                log.info("response from {} is {}", key, capacity);
                allCapacities.add(capacity);
            });
        }

        return allCapacities;
    }
}
