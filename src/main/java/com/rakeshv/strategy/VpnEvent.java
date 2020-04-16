package com.rakeshv.strategy;

import com.rakeshv.models.Vpn;
import com.rakeshv.repositories.VpnRepository;

import java.lang.reflect.InvocationTargetException;

public class VpnEvent implements EventType {
    private VpnRepository vpnRepository;

    public VpnEvent(VpnRepository repository) {
        this.vpnRepository = repository;

        if (vpnRepository.findAll().size() == 0) {
            Vpn vpn = Vpn.builder().build();
            vpnRepository.save(vpn);
        }
    }

    @Override
    public void processEvent(String[] action) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < action.length; i++) {
            sb.append(action[i].toLowerCase());
        }
        String function = sb.toString();

        try {
            this.getClass().getDeclaredMethod(function).invoke(this);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private Vpn getVpn() {
        return vpnRepository.findAll().get(0);
    }

    private void saveVpn(Vpn vpn) {
        vpnRepository.save(vpn);
    }

    public void remoteaccesscreate() {
        Vpn vpn = getVpn();
        vpn.setRemoteAccessCreate(vpn.getRemoteAccessCreate() + 1);
        saveVpn(vpn);
    }

    public void remoteaccessdestroy() {
        Vpn vpn = getVpn();
        vpn.setRemoteAccessDestroy(vpn.getRemoteAccessDestroy() + 1);
        saveVpn(vpn);
    }

    public void remoteaccessupdate() {
        Vpn vpn = getVpn();
        vpn.setRemoteAccessUpdate(vpn.getRemoteAccessUpdate() + 1);
        saveVpn(vpn);
    }

    public void useradd() {
        Vpn vpn = getVpn();
        vpn.setUserAdd(vpn.getUserAdd() + 1);
        saveVpn(vpn);
    }

    public void userremove() {
        Vpn vpn = getVpn();
        vpn.setUserRemove(vpn.getUserRemove() + 1);
        saveVpn(vpn);
    }

    public void s2svpngatewaycreate() {
        Vpn vpn = getVpn();
        vpn.setS2sVpnGatewayCreate(vpn.getS2sVpnGatewayCreate() + 1);
        saveVpn(vpn);
    }

    public void s2svpngatewaydelete() {
        Vpn vpn = getVpn();
        vpn.setS2sVpnGatewayDelete(vpn.getS2sVpnGatewayDelete() + 1);
        saveVpn(vpn);
    }

    public void s2svpngatewayupdate() {
        Vpn vpn = getVpn();
        vpn.setS2sVpnGatewayUpdate(vpn.getS2sVpnGatewayUpdate() + 1);
        saveVpn(vpn);
    }

    public void s2scustomergatewaycreate() {
        Vpn vpn =getVpn();
        vpn.setS2sCustomerGatewayCreate(vpn.getS2sCustomerGatewayCreate() + 1);
        saveVpn(vpn);
    }

    public void s2scustomergatewaydelete() {
        Vpn vpn = getVpn();
        vpn.setS2sCustomerGatewayDelete(vpn.getS2sCustomerGatewayDelete() + 1);
        saveVpn(vpn);
    }

    public void s2scustomergatewayupdate() {
        Vpn vpn = getVpn();
        vpn.setS2sCustomerGatewayUpdate(vpn.getS2sCustomerGatewayUpdate() + 1);
        saveVpn(vpn);
    }

    public void s2sconnectioncreate() {
        Vpn vpn = getVpn();
        vpn.setS2sConnectionCreate(vpn.getS2sConnectionCreate() + 1);
        saveVpn(vpn);
    }

    public void s2sconnectiondelete() {
        Vpn vpn = getVpn();
        vpn.setS2sConnectionDelete(vpn.getS2sConnectionDelete() + 1);
        saveVpn(vpn);
    }

    public void s2sconnectionreset() {
        Vpn vpn = getVpn();
        vpn.setS2sConnectionReset(vpn.getS2sConnectionReset() + 1);
        saveVpn(vpn);
    }

    public void s2sconnectionupdate() {
        Vpn vpn = getVpn();
        vpn.setS2sConnectionUpdate(vpn.getS2sConnectionUpdate() + 1);
        saveVpn(vpn);
    }
}
