package com.rakeshv.service;

import com.rakeshv.models.CloudstackCommand;
import com.rakeshv.models.CloudstackHandle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * CommandBuilderService
 */
@Service
@Slf4j
public class CommandBuilderService {

    @Autowired
    private CloudstackApiService cloudstackApiService;
    @Autowired
    private Environment environment;

    @Value("${cloudstack.private.platforms}")
    private String csrpPlatformsList;

    @Value("${cloudstack.public.platforms}")
    private String vpsPlatformsList;

    public HashMap<String, CloudstackHandle> platformMap;
    List<Callable<String>> callableList = new ArrayList<>();
    String[] csrpPlatforms;
    String[] vpsPlatforms;

    @PostConstruct
    private void constructHandlers() {
        platformMap = new HashMap<>();
        csrpPlatforms = csrpPlatformsList.split(",");
        vpsPlatforms = vpsPlatformsList.split(",");
        for (String platform : csrpPlatforms) {
            String url = environment.getProperty(platform + ".url");
            String apiKey = environment.getProperty(platform + ".apiKey");
            String secretKey = environment.getProperty(platform + ".secretKey");

            CloudstackHandle cloudstackHandle = CloudstackHandle.builder()
                    .url(url)
                    .apiKey(apiKey)
                    .secretKey(secretKey).build();

            platformMap.putIfAbsent(platform, cloudstackHandle);
        }

        for (String platform : vpsPlatforms) {
            String url = environment.getProperty(platform + ".url");
            String apiKey = environment.getProperty(platform + ".apiKey");
            String secretKey = environment.getProperty(platform + ".secretKey");

            CloudstackHandle cloudstackHandle = CloudstackHandle.builder()
                    .url(url)
                    .apiKey(apiKey)
                    .secretKey(secretKey).build();

            platformMap.putIfAbsent(platform, cloudstackHandle);
        }
    }

    public Map<String, String> executeOnAllPlatforms(String command, Map<String, String> parameters) {
        Map<String, String> publicPlatformMap = executeCommandOnPublicPlatforms(command, parameters);
        Map<String, String > privatePlatformMap = executeCommandOnPrivatePlatforms(command, parameters);

        publicPlatformMap.putAll(privatePlatformMap);
        return publicPlatformMap;
    }

    public Map<String, String> executeCommandOnPublicPlatforms(String command, Map<String,String> parameters) {

        return executeCommandOnPlatform(command, parameters, true);
    }

    public Map<String, String> executeCommandOnPrivatePlatforms(String command, Map<String,String> parameters) {

        return executeCommandOnPlatform(command, parameters, false);
    }

    private Map<String, String> executeCommandOnPlatform(String command, Map<String,String> parameters, boolean isPublic) {
        Map<String, String> resultMap = new HashMap<>();
        int NUMBER_OF_THREADS = isPublic ? vpsPlatforms.length : csrpPlatforms.length;
        String[] platforms = isPublic ? vpsPlatforms : csrpPlatforms;
        callableList = Arrays.asList(platforms)
                .stream()
                .parallel()
                .map(name -> {
                    Callable<String> callable = () -> {
                        String response = executeCommand(command, parameters, name);
                        if (response.contains("count")) {
                            resultMap.put(name, response);
                            return response;
                        }
                        return null;
                    };
                    return callable;
                })
                .collect(Collectors.toList());

        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        try {
            List<Future<String>> futures = executorService.invokeAll(callableList);
        } catch (InterruptedException e) {
            log.error("Exception happened {}", e.getMessage());
        }

        return resultMap;
    }

    String executeCommand(String command, Map<String, String> parameters, String platform) {
        CloudstackCommand cloudstackCommand = CloudstackCommand.builder()
                .command(command)
                .commandParameters(parameters).build();
        CloudstackHandle handle = platformMap.get(platform);
        return cloudstackApiService.executeCloudstackCommand(handle, cloudstackCommand);
    }
}