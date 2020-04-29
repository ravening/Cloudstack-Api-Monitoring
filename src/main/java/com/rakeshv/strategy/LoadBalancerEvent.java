package com.rakeshv.strategy;

import com.rakeshv.models.LoadBalancer;
import com.rakeshv.repositories.LoadBalancerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Component("LB")
public class LoadBalancerEvent implements EventType {
    @Autowired
    LoadBalancerRepository loadBalancerRepository;

    @PostConstruct
    public void postConstruct() {
        if (loadBalancerRepository.findAll().size() == 0) {
            LoadBalancer loadBalancer = LoadBalancer.builder().build();
            loadBalancerRepository.save(loadBalancer);
        }
    }
    @Override
    public void processEvent(String[] action) {
        String function = action.length > 2 ? action[1].toLowerCase() + action[2].toLowerCase() :
                                                action[1].toLowerCase();
        try {
            this.getClass().getDeclaredMethod(function).invoke(this);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private LoadBalancer getLoadBalancer() {
        List<LoadBalancer> loadBalancers = loadBalancerRepository.findAll();
        return loadBalancers.get(0);
    }

    private void saveLoadBalancer(LoadBalancer loadBalancer) {
        loadBalancerRepository.save(loadBalancer);
    }

    public void create() {
        LoadBalancer loadBalancer = getLoadBalancer();
        loadBalancer.setLbCreate(loadBalancer.getLbCreate() + 1);
        saveLoadBalancer(loadBalancer);
    }

    public void delete() {
        LoadBalancer loadBalancer = getLoadBalancer();
        loadBalancer.setLbDelete(loadBalancer.getLbDelete() + 1);
        saveLoadBalancer(loadBalancer);
    }

    public void update() {
        LoadBalancer loadBalancer = getLoadBalancer();
        loadBalancer.setLbUpdate(loadBalancer.getLbUpdate() + 1);
        saveLoadBalancer(loadBalancer);
    }

    public void certupload() {
        LoadBalancer loadBalancer = getLoadBalancer();
        loadBalancer.setLbCertUpload(loadBalancer.getLbCertUpload() + 1);
        saveLoadBalancer(loadBalancer);
    }

    public void certdelete() {
        LoadBalancer loadBalancer = getLoadBalancer();
        loadBalancer.setLbCertDelete(loadBalancer.getLbCertDelete() + 1);
        saveLoadBalancer(loadBalancer);
    }

    public void certassign() {
        LoadBalancer loadBalancer = getLoadBalancer();
        loadBalancer.setLbCertAssign(loadBalancer.getLbCertAssign() + 1);
        saveLoadBalancer(loadBalancer);
    }

    public void certremove() {
        LoadBalancer loadBalancer = getLoadBalancer();
        loadBalancer.setLbCertRemove(loadBalancer.getLbCertRemove() + 1);
        saveLoadBalancer(loadBalancer);
    }

    public void assignto() {
        LoadBalancer loadBalancer = getLoadBalancer();
        loadBalancer.setLbAssignToRule(loadBalancer.getLbAssignToRule() + 1);
        saveLoadBalancer(loadBalancer);
    }

    public void removefrom() {
        LoadBalancer loadBalancer = getLoadBalancer();
        loadBalancer.setLbRemoveFromRule(loadBalancer.getLbRemoveFromRule() + 1);
        saveLoadBalancer(loadBalancer);
    }
}
