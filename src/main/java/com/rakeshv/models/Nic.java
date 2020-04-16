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
public class Nic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private long nicCreate;
    private long nicDelete;
    private long nicUpdate;
}

