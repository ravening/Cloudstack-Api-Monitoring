package com.rakeshv.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakeshv.models.CloudstackEvent;
import com.rakeshv.models.cloudstackresponse.Event;
import com.rakeshv.models.cloudstackresponse.EventResponse;
import com.rakeshv.models.cloudstackresponse.ListEventsResponse;
import com.rakeshv.repositories.AccountRepository;
import com.rakeshv.repositories.DomainRepository;
import com.rakeshv.repositories.LoadBalancerRepository;
import com.rakeshv.repositories.NetworkRepository;
import com.rakeshv.repositories.NicRepository;
import com.rakeshv.repositories.SecurityGroupRepository;
import com.rakeshv.repositories.TemplateRepository;
import com.rakeshv.repositories.UserRepository;
import com.rakeshv.repositories.VmRespository;
import com.rakeshv.repositories.VncRepository;
import com.rakeshv.repositories.VolumeRepository;
import com.rakeshv.repositories.VpnRepository;
import com.rakeshv.strategy.AccountEvent;
import com.rakeshv.strategy.DomainEvent;
import com.rakeshv.strategy.EventType;
import com.rakeshv.strategy.LoadBalancerEvent;
import com.rakeshv.strategy.NetworkEvent;
import com.rakeshv.strategy.NicEvent;
import com.rakeshv.strategy.SgEvent;
import com.rakeshv.strategy.TemplateEvent;
import com.rakeshv.strategy.UserEvent;
import com.rakeshv.strategy.VmEvent;
import com.rakeshv.strategy.VncEvent;
import com.rakeshv.strategy.VolumeEvent;
import com.rakeshv.strategy.VpnEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class EventsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    VmRespository vmRespository;
    @Autowired
    NicRepository nicRepository;
    @Autowired
    LoadBalancerRepository loadBalancerRepository;
    @Autowired
    VncRepository vncRepository;
    @Autowired
    DomainRepository domainRepository;
    @Autowired
    VolumeRepository volumeRepository;
    @Autowired
    CloudstackEventService cloudstackEventService;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TemplateRepository templateRepository;
    @Autowired
    VpnRepository vpnRepository;
    @Autowired
    SecurityGroupRepository securityGroupRepository;
    @Autowired
    NetworkRepository networkRepository;

    Map<String, EventType> eventTypeMap;

    private final ApplicationEventPublisher eventPublisher;

    public EventsService(ApplicationEventPublisher publisher) {
        this.eventPublisher = publisher;
    }

    private CloudstackEvent cloudstackEvent;
    ObjectMapper mapper;

    ScheduledExecutorService scheduledExecutorService;

    @PostConstruct
    public void initTables() {
        eventTypeMap = new HashMap<>();
        eventTypeMap.putIfAbsent("user", new UserEvent(userRepository));
        eventTypeMap.putIfAbsent("vm", new VmEvent(vmRespository));
        eventTypeMap.putIfAbsent("nic", new NicEvent(nicRepository));
        eventTypeMap.putIfAbsent("lb", new LoadBalancerEvent(loadBalancerRepository));
        eventTypeMap.putIfAbsent("vnc", new VncEvent(vncRepository));
        eventTypeMap.putIfAbsent("volume", new VolumeEvent(volumeRepository));
        eventTypeMap.putIfAbsent("domain", new DomainEvent(domainRepository));
        eventTypeMap.putIfAbsent("account", new AccountEvent(accountRepository));
        eventTypeMap.putIfAbsent("template", new TemplateEvent(templateRepository));
        eventTypeMap.putIfAbsent("vpn", new VpnEvent(vpnRepository));
        eventTypeMap.putIfAbsent("sg", new SgEvent(securityGroupRepository));
        eventTypeMap.putIfAbsent("network", new NetworkEvent(networkRepository));

        cloudstackEvent = CloudstackEvent.builder().build();
        mapper = new ObjectMapper();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(runnable, 0, 60, TimeUnit.SECONDS);
    }

    public void processEvents() throws IOException {
        log.info("Getting all the events");
        Map<String, String> resultMap = cloudstackEventService.listEvents();

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
                            }
                        }

                );

            }
        }
    }

    Runnable runnable = () -> {
        try {
            processEvents();
        } catch (Exception e) {
            log.error("Exceptiuon: {}", e.getMessage());
        }
    };

}
