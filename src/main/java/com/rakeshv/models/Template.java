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
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private long templateCreate;
    private long templateDelete;
    private long templateUpdate;
    private long templateDownloadStart;
    private long templateDownloadSuccess;
    private long templateDownloadFailed;
    private long templateCopy;
    private long templateExtract;
    private long templateUpload;
    private long templateCleanup;
}
