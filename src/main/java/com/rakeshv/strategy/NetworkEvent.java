package com.rakeshv.strategy;

import com.rakeshv.models.Network;
import com.rakeshv.repositories.NetworkRepository;

import java.lang.reflect.InvocationTargetException;

public class NetworkEvent implements EventType {
    private NetworkRepository networkRepository;

    public NetworkEvent(NetworkRepository repository) {
        this.networkRepository = repository;

        if (networkRepository.findAll().size() == 0) {
            Network network = Network.builder().build();
            networkRepository.save(network);
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

    private Network getNetwork() {
        return networkRepository.findAll().get(0);
    }

    private void saveNetwork(Network network) {
        networkRepository.save(network);
    }

    public void create() {
        Network network = getNetwork();
        network.setNetworkCreate(network.getNetworkCreate() + 1);
        saveNetwork(network);
    }

    public void delete() {
        Network network = getNetwork();
        network.setNetworkDelete(network.getNetworkDelete() + 1);
        saveNetwork(network);
    }

    public void update() {
        Network network = getNetwork();
        network.setNetworkUpdate(network.getNetworkUpdate() + 1);
        saveNetwork(network);
    }

    public void migrate() {
        Network network = getNetwork();
        network.setNetworkMigrate(network.getNetworkMigrate() + 1);
        saveNetwork(network);
    }

    public void restart() {
        Network network = getNetwork();
        network.setNetworkRestart(network.getNetworkRestart() + 1);
        saveNetwork(network);
    }

    public void aclcreate() {
        Network network = getNetwork();
        network.setNetworkAclCreate(network.getNetworkAclCreate() + 1);
        saveNetwork(network);
    }

    public void acldelete() {
        Network network = getNetwork();
        network.setNetworkAclDelete(network.getNetworkAclDelete() + 1);
        saveNetwork(network);
    }

    public void aclreplace() {
        Network network = getNetwork();
        network.setNetworkAclReplace(network.getNetworkAclReplace() + 1);
        saveNetwork(network);
    }

    public void aclupdate() {
        Network network = getNetwork();
        network.setNetworkAclUpdate(network.getNetworkAclUpdate() + 1);
        saveNetwork(network);
    }

    public void aclitemcreate() {
        Network network = getNetwork();
        network.setNetworkAclItemCreate(network.getNetworkAclItemCreate() + 1);
        saveNetwork(network);
    }

    public void aclitemupdate() {
        Network network = getNetwork();
        network.setNetworkAclItemUpdate(network.getNetworkAclItemUpdate() + 1);
        saveNetwork(network);
    }

    public void aclitemdelete() {
        Network network = getNetwork();
        network.setNetworkAclItemDelete(network.getNetworkAclItemDelete() + 1);
        saveNetwork(network);
    }

    public void elementconfigure() {
        Network network = getNetwork();
        network.setNetworkElementConfigure(network.getNetworkElementConfigure() + 1);
        saveNetwork(network);
    }

    public void offeringcreate() {
        Network network = getNetwork();
        network.setNetworkOfferingCreate(network.getNetworkOfferingCreate() + 1);
        saveNetwork(network);
    }

    public void offeringassign() {
        Network network = getNetwork();
        network.setNetworkOfferingAssign(network.getNetworkOfferingAssign() + 1);
        saveNetwork(network);
    }

    public void offeringedit() {
        Network network = getNetwork();
        network.setNetworkOfferingEdit(network.getNetworkOfferingEdit() + 1);
        saveNetwork(network);
    }

    public void offeringremove() {
        Network network = getNetwork();
        network.setNetworkOfferingRemove(network.getNetworkOfferingRemove() + 1);
        saveNetwork(network);
    }

    public void offeringdelete() {
        Network network = getNetwork();
        network.setNetworkOfferingDelete(network.getNetworkOfferingDelete() + 1);
        saveNetwork(network);
    }
}
