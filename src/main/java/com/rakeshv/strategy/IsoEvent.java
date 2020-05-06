package com.rakeshv.strategy;

import com.rakeshv.models.Iso;
import com.rakeshv.repositories.IsoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;

@Component("ISO")
public class IsoEvent implements EventType {
    @Autowired
    IsoRepository isoRepository;

    @PostConstruct
    public void postContruct() {
        if (isoRepository.findAll().size() == 0) {
            Iso iso = Iso.builder().build();
            isoRepository.save(iso);
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

    public void saveIso(Iso iso) {
        isoRepository.save(iso);
    }

    public Iso getIso() {
        return isoRepository.findAll().get(0);
    }

    public void create() {
        Iso iso = getIso();
        iso.setIsoCreate(iso.getIsoCreate() + 1);
        saveIso(iso);
    }

    public void delete() {
        Iso iso = getIso();
        iso.setIsoDelete(iso.getIsoDelete() + 1);
        saveIso(iso);
    }

    public void copy() {
        Iso iso = getIso();
        iso.setIsoCopy(iso.getIsoCopy() + 1);
        saveIso(iso);
    }

    public void attach() {
        Iso iso = getIso();
        iso.setIsoAttach(iso.getIsoAttach() + 1);
        saveIso(iso);
    }

    public void detach() {
        Iso iso = getIso();
        iso.setIsoDetach(iso.getIsoDetach() + 1);
        saveIso(iso);
    }

    public void extract() {
        Iso iso = getIso();
        iso.setIsoExtract(iso.getIsoExtract() + 1);
        saveIso(iso);
    }

    public void upload() {
        Iso iso = getIso();
        iso.setIsoUpload(iso.getIsoUpload() + 1);
        saveIso(iso);
    }
}
