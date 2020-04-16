package com.rakeshv.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Domain {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long domainCreate;
    private long domainDelete;
    private long domainUpdate;
}
