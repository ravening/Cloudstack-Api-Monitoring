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
public class Volume {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private long volumeCreate;
    private long volumeDelete;
    private long volumeAttach;
    private long volumeDetach;
    private long volumeExtract;
    private long volumeUpload;
    private long volumeMigrate;
    private long volumeResize;
    private long volumeUpdate;
    private long volumeDestroy;
}
