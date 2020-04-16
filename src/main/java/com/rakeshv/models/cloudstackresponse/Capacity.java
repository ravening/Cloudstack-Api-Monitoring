package com.rakeshv.models.cloudstackresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Capacity {
    private Long capacityallocated;
    private Long capacitytotal;
    private Long capacityused;
    private String name;
    private String percentused;
    private int type;
    private String zonename;
}
