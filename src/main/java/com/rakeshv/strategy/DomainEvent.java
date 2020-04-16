package com.rakeshv.strategy;

import com.rakeshv.models.Domain;
import com.rakeshv.repositories.DomainRepository;

import java.lang.reflect.InvocationTargetException;

public class DomainEvent implements EventType {
    private final DomainRepository domainRepository;

    public DomainEvent(DomainRepository repository) {
        this.domainRepository = repository;

        if (domainRepository.findAll().size() == 0) {
            Domain domain = Domain.builder().build();
            domainRepository.save(domain);
        }
    }

    @Override
    public void processEvent(String[] action) {
        try {
            this.getClass().getDeclaredMethod(action[1].toLowerCase()).invoke(this);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void create() {
        Domain domain = domainRepository.findAll().get(0);
        domain.setDomainCreate(domain.getDomainCreate() + 1);
        domainRepository.save(domain);
    }

    public void delete() {
        Domain domain = domainRepository.findAll().get(0);
        domain.setDomainDelete(domain.getDomainDelete() + 1);
        domainRepository.save(domain);
    }

    public void update() {
        Domain domain = domainRepository.findAll().get(0);
        domain.setDomainUpdate(domain.getDomainUpdate() + 1);
        domainRepository.save(domain);
    }
}
