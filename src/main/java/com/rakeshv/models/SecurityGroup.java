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
public class SecurityGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private long sgCreate;
    private long sgDelete;
    private long sgAssign;
    private long sgRemove;
    private long sgUpdate;
    private long sgAuthIngress;
    private long sgRevokeIngress;
    private long sgAuthEgress;
    private long sgRevokeEgress;
}
