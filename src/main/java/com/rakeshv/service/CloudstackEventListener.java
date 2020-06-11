package com.rakeshv.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakeshv.models.ApiCount;
import com.rakeshv.models.ApiCountPerDomain;
import com.rakeshv.models.CloudstackEvent;
import com.rakeshv.models.cloudstackresponse.Event;
import com.rakeshv.repositories.ApiCountPerDomainRepository;
import com.rakeshv.repositories.ApiCountRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;
import reactor.kafka.sender.SenderRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Slf4j
public class CloudstackEventListener {
    private final ApiCountRepository apiCountRepository;
    private final ApiCountPerDomainRepository apiCountPerDomainRepository;
    private KafkaSender kafkaSender;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public CloudstackEventListener(ApiCountRepository repository,
                                   ApiCountPerDomainRepository apiCountPerDomainRepository) {
        this.apiCountRepository = repository;
        this.apiCountPerDomainRepository = apiCountPerDomainRepository;

        final Map<String, Object> producerProps = new HashMap<>();
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProps.put(ProducerConfig.ACKS_CONFIG, "all");
        producerProps.put(ProducerConfig.CLIENT_ID_CONFIG, "test-events-1");
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        final SenderOptions<Integer, String> producerOptions = SenderOptions.create(producerProps);
        kafkaSender = KafkaSender.create(producerOptions);
    }

    @Async
    @EventListener
    public void processCloudstackEvents(CloudstackEvent cloudstackEvent) {
        List<Event> events = cloudstackEvent.getEvents();
        log.info("Processing cloudstack events");
        events.forEach(event -> {
            SenderRecord<Integer, String, Integer> message =
                    SenderRecord.create(new ProducerRecord<>("test-events", toBinary(event)), 1);
            // Uncomment below code to send events to kafak on "test-events" topic
            //            kafkaSender.send(Mono.just(message))
            //                    .doOnNext(r -> log.info("Message sent {}", r))
            //                    .doOnError(e -> log.error("Error sending message {}", e))
            //                    .subscribe();
            if (event.getState().equalsIgnoreCase("Completed") &&
                    !(event.getUsername().equalsIgnoreCase("admin") &&
                            event.getDomain().equalsIgnoreCase("ROOT")) &&
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

                Optional<ApiCountPerDomain> apiCountPerDomainOptional =
                        apiCountPerDomainRepository.findByDomainNameEqualsAndApiEquals(event.getDomain(), event.getType());
                ApiCountPerDomain apiCountPerDomain;
                if (apiCountPerDomainOptional.isPresent()) {
                        apiCountPerDomain = apiCountPerDomainOptional.get();
                        apiCountPerDomain.setCount(apiCountPerDomain.getCount() + 1);
                    } else {
                        apiCountPerDomain = ApiCountPerDomain.builder()
                                       .domainName(event.getDomain())
                                       .api(event.getType())
                                       .count(1L).build();
                    }
                apiCountPerDomainRepository.save(apiCountPerDomain);
            }
        });
    }

    private String toBinary(Object object) {
        try {
                return objectMapper.writeValueAsString(object);
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException(e);
            }
    }
}
