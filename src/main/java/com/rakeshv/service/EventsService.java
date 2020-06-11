package com.rakeshv.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakeshv.models.CloudstackEvent;
import com.rakeshv.models.cloudstackresponse.Event;
import com.rakeshv.models.cloudstackresponse.EventResponse;
import com.rakeshv.models.cloudstackresponse.ListEventsResponse;
import com.rakeshv.strategy.EventType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
public class EventsService {
    @Autowired
    CloudstackEventService cloudstackEventService;
    @Autowired
    MicrometerEventsService micrometerEventsService;

    Map<String, EventType> eventTypeMap;

    private final ApplicationEventPublisher eventPublisher;

    public EventsService(ApplicationEventPublisher publisher,
                         Map<String, EventType> eventMap) {
        this.eventPublisher = publisher;
        this.eventTypeMap = eventMap;
    }

    private CloudstackEvent cloudstackEvent;
    ObjectMapper mapper;

    ScheduledExecutorService scheduledExecutorService;

    @PostConstruct
    public void initTables() {
        cloudstackEvent = CloudstackEvent.builder().build();
        mapper = new ObjectMapper();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(runnable, 0, 60, TimeUnit.SECONDS);
    }

    public void processEvents() throws IOException {
        log.info("Getting all the events");
        Map<String, String> resultMap = cloudstackEventService.listEvents();
        AtomicReference<Double> count = new AtomicReference<>((double) 0);

        for (String key : resultMap.keySet()) {
            ListEventsResponse listEventsResponse = mapper.readValue(resultMap.get(key), ListEventsResponse.class);
            if (listEventsResponse != null) {
                EventResponse eventResponse = listEventsResponse.getListeventsresponse();
                List<Event> eventList = eventResponse.getEvent();
                cloudstackEvent.setEvents(eventList);
                eventPublisher.publishEvent(cloudstackEvent);
                eventList.forEach(
                        event -> {
                            String[] type = event.getType().split("\\.");

                            if (eventTypeMap.containsKey(type[0].toLowerCase()) &&
                                    event.getState().equalsIgnoreCase("Completed") &&
                                    !event.getDomain().equalsIgnoreCase("ROOT") &&
                                    !event.getUsername().equalsIgnoreCase("system")) {
                                log.info("Platform: {} {}", key,event);
                                eventTypeMap.get(type[0].toLowerCase()).processEvent(type);
                                count.getAndSet((double) (count.get() + 1));
                            }
                        }

                );

            }
        }
        micrometerEventsService.setApiCountPerMinute(count.get());
    }

    Runnable runnable = () -> {
        try {
            processEvents();
        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
        }
    };

}
