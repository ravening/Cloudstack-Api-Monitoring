package com.rakeshv.service;

import com.rakeshv.models.ApiCount;
import com.rakeshv.models.CloudstackEvent;
import com.rakeshv.models.cloudstackresponse.Event;
import com.rakeshv.repositories.ApiCountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class CloudstackEventListener {
    private final ApiCountRepository apiCountRepository;

    public CloudstackEventListener(ApiCountRepository repository) {
        this.apiCountRepository = repository;
    }

    @Async
    @EventListener
    public void processCloudstackEvents(CloudstackEvent cloudstackEvent) {
        List<Event> events = cloudstackEvent.getEvents();
        log.info("Processing cloudstack events");
        events.forEach(event -> {
            if (event.getState().equalsIgnoreCase("Completed") &&
                    !event.getDomain().equalsIgnoreCase("ROOT") &&
                    !event.getUsername().equalsIgnoreCase("system")) {

                Optional<ApiCount> apiCountOptional = apiCountRepository
                        .findByDomainNameContains(event.getDomain().trim());

                ApiCount apiCount;
                if (apiCountOptional.isPresent()) {
                    apiCount = apiCountOptional.get();
                    apiCount.setCount(apiCount.getCount() + 1);
                } else {
                    apiCount = ApiCount.builder()
                            .count(1)
                            .domainName(event.getDomain()).build();
                }

                apiCountRepository.save(apiCount);
            }
        });
    }
}
