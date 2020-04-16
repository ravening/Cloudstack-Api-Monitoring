package com.rakeshv.strategy;

import com.rakeshv.models.Nic;
import com.rakeshv.repositories.NicRepository;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class NicEvent implements EventType {
    private final NicRepository nicRepository;

    public NicEvent(NicRepository repository) {
        this.nicRepository = repository;

        if (nicRepository.findAll().size() == 0) {
            Nic nic = Nic.builder().build();
            nicRepository.save(nic);
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

    private Nic getNic() {
        List<Nic> nics = nicRepository.findAll();
        return nics.get(0);
    }

    private void saveNic(Nic nic) {
        nicRepository.save(nic);
    }

    public void create() {
        Nic nic = getNic();
        nic.setNicCreate(nic.getNicCreate() + 1);
        saveNic(nic);
    }

    public void delete() {
        Nic nic = getNic();
        nic.setNicDelete(nic.getNicDelete() + 1);
        saveNic(nic);
    }

    public void update() {
        Nic nic = getNic();
        nic.setNicUpdate(nic.getNicUpdate() + 1);
        saveNic(nic);
    }
}
