package com.rakeshv.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class CloudstackEventService {
    @Autowired
    private CommandBuilderService commandBuilderService;

    public Map<String, String> listEvents() throws IOException {
        Map<String, String> parameters = new HashMap<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime last = LocalDateTime.now().minusMinutes(1);

        parameters.putIfAbsent("listall", "true");
        parameters.putIfAbsent("startdate", dtf.format(last));
        parameters.putIfAbsent("enddate", dtf.format(now));

        return commandBuilderService.executeOnAllPlatforms("listEvents", parameters);
    }
}
