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
public class Network {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private long networkCreate;
    private long networkDelete;
    private long networkUpdate;
    private long networkMigrate;
    private long networkRestart;
    private long networkAclCreate;
    private long networkAclDelete;
    private long networkAclReplace;
    private long networkAclUpdate;
    private long networkAclItemCreate;
    private long networkAclItemUpdate;
    private long networkAclItemDelete;
    private long networkElementConfigure;
    private long networkOfferingCreate;
    private long networkOfferingAssign;
    private long networkOfferingEdit;
    private long networkOfferingRemove;
    private long networkOfferingDelete;
}
