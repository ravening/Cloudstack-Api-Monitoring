package com.rakeshv.strategy;

import com.rakeshv.models.Vnc;
import com.rakeshv.repositories.VncRepository;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class VncEvent implements EventType {
    private final VncRepository vncRepository;

    public VncEvent(VncRepository repository) {
        this.vncRepository = repository;

        if (vncRepository.findAll().size() == 0) {
            Vnc vnc = Vnc.builder().build();
            vncRepository.save(vnc);
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

    private Vnc getVnc() {
        List<Vnc> vncs = vncRepository.findAll();
        return vncs.get(0);
    }

    private void saveVnc(Vnc vnc) {
        vncRepository.save(vnc);
    }

    public void connect() {
        Vnc vnc = getVnc();
        vnc.setConnect(vnc.getConnect() + 1);
        saveVnc(vnc);
    }

    public void disconnect() {
        Vnc vnc = getVnc();
        vnc.setDisconnect(vnc.getDisconnect() + 1);
        saveVnc(vnc);
    }
}
