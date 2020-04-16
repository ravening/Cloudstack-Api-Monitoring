package com.rakeshv.models;

import com.rakeshv.models.cloudstackresponse.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class CloudstackEvent {
    private List<Event> events;
}
