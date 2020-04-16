package com.rakeshv.service;

import com.rakeshv.models.CloudstackCommand;
import com.rakeshv.models.CloudstackHandle;
import org.springframework.stereotype.Service;

import br.com.autonomiccs.apacheCloudStack.client.ApacheCloudStackClient;
import br.com.autonomiccs.apacheCloudStack.client.ApacheCloudStackRequest;
import br.com.autonomiccs.apacheCloudStack.client.beans.ApacheCloudStackUser;

/**
 * CloudstackApiService
 */
@Service
public class CloudstackApiService {

    private String execute(CloudstackHandle object, CloudstackCommand cloudstackCommand) {
        ApacheCloudStackUser apacheCloudStackUser = new ApacheCloudStackUser(object.getSecretKey(), object.getApiKey());
        ApacheCloudStackClient apacheCloudStackClient = new ApacheCloudStackClient(object.getUrl(), apacheCloudStackUser);
        ApacheCloudStackRequest apacheCloudStackRequest = new ApacheCloudStackRequest(cloudstackCommand.getCommand());
        apacheCloudStackRequest.addParameter("response", "json");

        if (cloudstackCommand.getCommandParameters() != null) {
            for (String key : cloudstackCommand.getCommandParameters().keySet()) {
                apacheCloudStackRequest.addParameter(key, cloudstackCommand.getCommandParameters().get(key));
            }
        }

        return apacheCloudStackClient.executeRequest(apacheCloudStackRequest);
    }

    public String executeCloudstackCommand(CloudstackHandle handle, CloudstackCommand command) {
        return execute(handle, command);
    }
}