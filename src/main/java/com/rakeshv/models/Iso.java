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
public class Iso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long isoCreate;
    private Long isoDelete;
    private Long isoCopy;
    private Long isoAttach;
    private Long isoDetach;
    private Long isoExtract;
    private Long isoUpload;
}
