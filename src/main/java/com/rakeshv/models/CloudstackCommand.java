package com.rakeshv.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * CloudstackCommand
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CloudstackCommand {

    private String command;
    Map<String, String> commandParameters;
}