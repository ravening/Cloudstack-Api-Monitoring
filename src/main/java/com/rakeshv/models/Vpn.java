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
public class Vpn {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private long remoteAccessCreate;
    private long remoteAccessDestroy;
    private long remoteAccessUpdate;
    private long userAdd;
    private long userRemove;
    private long s2sVpnGatewayCreate;
    private long s2sVpnGatewayDelete;
    private long s2sVpnGatewayUpdate;
    private long s2sCustomerGatewayCreate;
    private long s2sCustomerGatewayDelete;
    private long s2sCustomerGatewayUpdate;
    private long s2sConnectionCreate;
    private long s2sConnectionDelete;
    private long s2sConnectionReset;
    private long s2sConnectionUpdate;

}
