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
public class LoadBalancer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private long lbCreate;
    private long lbDelete;
    private long lbUpdate;
    private long lbCertUpload;
    private long lbCertDelete;
    private long lbCertAssign;
    private long lbCertRemove;
    private long lbAssignToRule;
    private long lbRemoveFromRule;
}
