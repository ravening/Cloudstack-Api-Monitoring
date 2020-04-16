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

        if (apiCountRepository.findAll().size() == 0) {
            ApiCount apiCount = ApiCount.builder().build();
            apiCountRepository.save(apiCount);
        }
    }

    @Async
    @EventListener
    public void processCloudstackEvents(CloudstackEvent cloudstackEvent) {
        List<Event> events = cloudstackEvent.getEvents();
        log.info("Processing cloudstack events");
        events.forEach(event -> {
            if (event.getState().equalsIgnoreCase("Completed") &&
                    !event.getDomain().equalsIgnoreCase("ROOT") &&
                    !event.getAccount().equalsIgnoreCase("system")) {

                Optional<ApiCount> apiCountOptional = apiCountRepository
                        .findByDomainNameEqualsIgnoreCase(event.getDomain());

                ApiCount apiCount;
                if (apiCountOptional.isPresent()) {
                    apiCount = apiCountOptional.get();
                    apiCount.setCount(apiCount.getCount() + 1);
                } else {
                    apiCount = ApiCount.builder().count(1).build();
                }

                apiCountRepository.save(apiCount);
            }
        });
    }
}
