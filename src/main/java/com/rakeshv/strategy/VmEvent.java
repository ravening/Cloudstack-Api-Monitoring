package com.rakeshv.strategy;

import com.rakeshv.models.Vm;
import com.rakeshv.repositories.VmRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Component("VM")
public class VmEvent implements EventType {
    @Autowired
    VmRespository vmRespository;

    @PostConstruct
    public void postConstruct() {
        if (vmRespository.findAll().size() == 0) {
            Vm vm = Vm.builder().build();
            vmRespository.save(vm);
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

    private Vm getVm() {
        List<Vm> vms = vmRespository.findAll();
        return vms.get(0);
    }

    private void saveVm(Vm vm) {
        vmRespository.save(vm);
    }

    public void create() {
        Vm vm = getVm();
        vm.setVmCreate(vm.getVmCreate() + 1);
        saveVm(vm);
    }

    public void destroy() {
        Vm vm = getVm();
        vm.setVmDestroy(vm.getVmDestroy() + 1);
        saveVm(vm);
    }

    public void start() {
        Vm vm = getVm();
        vm.setVmStart(vm.getVmStart() + 1);
        saveVm(vm);
    }

    public void stop() {
        Vm vm = getVm();
        vm.setVmStop(vm.getVmStop() + 1);
        saveVm(vm);
    }

    public void migrate() {
        Vm vm = getVm();
        vm.setVmMigrate(vm.getVmMigrate() + 1);
        saveVm(vm);
    }

    public void reboot() {
        Vm vm = getVm();
        vm.setVmReboot(vm.getVmReboot() + 1);
        saveVm(vm);
    }

    public void update() {
        Vm vm = getVm();
        vm.setVmUpdate(vm.getVmUpdate() + 1);
        saveVm(vm);
    }

    public void upgrade() {
        Vm vm = getVm();
        vm.setVmUpgrade(vm.getVmUpgrade() + 1);
        saveVm(vm);
    }

    public void resetpassword() {
        Vm vm = getVm();
        vm.setVmResetPassword(vm.getVmResetPassword() + 1);
        saveVm(vm);
    }

    public void expunge() {
        Vm vm = getVm();
        vm.setVmExpunge(vm.getVmExpunge() + 1);
        saveVm(vm);
    }

    public void restore() {
        Vm vm = getVm();
        vm.setVmRestore(vm.getVmRestore() + 1);
        saveVm(vm);
    }

    public void resetsshkey() {
        Vm vm = getVm();
        vm.setVmResetsshkey(vm.getVmResetsshkey() + 1);
        saveVm(vm);
    }

    public void resetsecuritygroups() {
        Vm vm = getVm();
        vm.setVmResetsecuritygroups(vm.getVmResetsecuritygroups() + 1);
        saveVm(vm);
    }

    public void cancelmigration() {
        Vm vm = getVm();
        vm.setVmCancelMigration(vm.getVmCancelMigration() + 1);
        saveVm(vm);
    }
}
