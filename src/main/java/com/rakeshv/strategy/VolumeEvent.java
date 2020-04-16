package com.rakeshv.strategy;

import com.rakeshv.models.Volume;
import com.rakeshv.repositories.VolumeRepository;

import java.lang.reflect.InvocationTargetException;

public class VolumeEvent implements EventType {
    private final VolumeRepository volumeRepository;

    public VolumeEvent(VolumeRepository repository) {
        this.volumeRepository = repository;

        if (volumeRepository.findAll().size() == 0) {
            Volume volume = Volume.builder().build();
            volumeRepository.save(volume);
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

    private Volume getVolume() {
        return volumeRepository.findAll().get(0);
    }

    private void saveVolume(Volume volume) {
        volumeRepository.save(volume);
    }

    public void create() {
        Volume volume = getVolume();
        volume.setVolumeCreate(volume.getVolumeCreate() + 1);
        saveVolume(volume);
    }

    public void delete() {
        Volume volume = getVolume();
        volume.setVolumeDelete(volume.getVolumeDelete() + 1);
        saveVolume(volume);
    }

    public void attach() {
        Volume volume = getVolume();
        volume.setVolumeAttach(volume.getVolumeAttach() + 1);
        saveVolume(volume);
    }

    public void detach() {
        Volume volume = getVolume();
        volume.setVolumeDetach(volume.getVolumeDetach() + 1);
        saveVolume(volume);
    }

    public void extract() {
        Volume volume = getVolume();
        volume.setVolumeExtract(volume.getVolumeExtract() + 1);
        saveVolume(volume);
    }

    public void upload() {
        Volume volume = getVolume();
        volume.setVolumeUpload(volume.getVolumeUpload() + 1);
        saveVolume(volume);
    }

    public void migrate() {
        Volume volume = getVolume();
        volume.setVolumeMigrate(volume.getVolumeMigrate() + 1);
        saveVolume(volume);
    }

    public void resize() {
        Volume volume = getVolume();
        volume.setVolumeResize(volume.getVolumeResize() + 1);
        saveVolume(volume);
    }

    public void update() {
        Volume volume = getVolume();
        volume.setVolumeUpdate(volume.getVolumeUpdate() + 1);
        saveVolume(volume);
    }

    public void destroy() {
        Volume volume = getVolume();
        volume.setVolumeDestroy(volume.getVolumeDestroy() + 1);
        saveVolume(volume);
    }
}
