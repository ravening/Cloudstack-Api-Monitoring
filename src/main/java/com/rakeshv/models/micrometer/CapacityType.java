package com.rakeshv.models.micrometer;

public enum CapacityType {
    CAPACITY_ALLOCATED("capacity_allocated", "Total allocated capacity"),
    CAPACITY_TOTAL("capacity_total", "Total capacity"),
    CAPACITY_USED("capacity_used", "Total used capacity"),
    PERCENT_USED("percent_used", "Used percentage")
    ;

    private String counterName;
    private String description;

    private CapacityType(String name, String description) {
        this.counterName = name;
        this.description = description;
    }

    public String getCounterName() {
        return this.counterName;
    }

    public String getDescription() {
        return this.description;
    }
}
