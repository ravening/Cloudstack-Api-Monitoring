package com.rakeshv.strategy;

import com.rakeshv.models.SecurityGroup;
import com.rakeshv.repositories.SecurityGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;

@Component("SG")
public class SgEvent implements EventType {
    @Autowired
    SecurityGroupRepository securityGroupRepository;

    @PostConstruct
    public void postConstruct() {
        if (securityGroupRepository.findAll().size() == 0) {
            SecurityGroup securityGroup = SecurityGroup.builder().build();
            securityGroupRepository.save(securityGroup);
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

    private SecurityGroup getSg() {
        return securityGroupRepository.findAll().get(0);
    }

    private void saveSg(SecurityGroup securityGroup) {
        securityGroupRepository.save(securityGroup);
    }

    public void create() {
        SecurityGroup securityGroup = getSg();
        securityGroup.setSgCreate(securityGroup.getSgCreate() + 1);
        saveSg(securityGroup);
    }

    public void delete() {
        SecurityGroup securityGroup = getSg();
        securityGroup.setSgDelete(securityGroup.getSgDelete() + 1);
        saveSg(securityGroup);
    }

    public void assign() {
        SecurityGroup securityGroup = getSg();
        securityGroup.setSgAssign(securityGroup.getSgAssign() + 1);
        saveSg(securityGroup);
    }

    public void remove() {
        SecurityGroup securityGroup = getSg();
        securityGroup.setSgRemove(securityGroup.getSgRemove() + 1);
        saveSg(securityGroup);
    }

    public void update() {
        SecurityGroup securityGroup = getSg();
        securityGroup.setSgUpdate(securityGroup.getSgUpdate() + 1);
        saveSg(securityGroup);
    }

    public void authingress() {
        SecurityGroup securityGroup = getSg();
        securityGroup.setSgAuthIngress(securityGroup.getSgAuthIngress() + 1);
        saveSg(securityGroup);
    }

    public void revokeingress() {
        SecurityGroup securityGroup = getSg();
        securityGroup.setSgRevokeIngress(securityGroup.getSgRevokeIngress() + 1);
        saveSg(securityGroup);
    }

    public void authegress() {
        SecurityGroup securityGroup = getSg();
        securityGroup.setSgAuthEgress(securityGroup.getSgAuthEgress() + 1);
        saveSg(securityGroup);
    }

    public void revokeegress() {
        SecurityGroup securityGroup = getSg();
        securityGroup.setSgRevokeEgress(securityGroup.getSgRevokeEgress() + 1);
        saveSg(securityGroup);
    }
}
