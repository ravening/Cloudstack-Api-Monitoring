package com.rakeshv.models.micrometer;

public enum EventType {
    VM_CREATE("vm_create", "Total VM's deployed"),
    VM_DESTROY("vm_destroy", "Total VM's destroyed"),
    VM_START("vm_start", "Total VM's started"),
    VM_STOP("vm_stop", "Total VM's stopped"),
    VM_REBOOT("vm_reboot", "Total VM's rebooted"),
    VM_UPDATE("vm_update", "Total VM's update"),
    VM_UPGRADE("vm_upgrade", "Total VM's upgraded"),
    VM_RESETPASSWORD("vm_resetpassword", "Total VM's password reset"),
    VM_RESETSSHKEY("vm_resetsshkey", "Total Vm's ssh key reset"),
    VM_RESET_SECURITYGROUP("vm_reset_securitygroup", "Total Vm's security group reset"),
    VM_MIGRATE("vm_migrate", "Total VM migrations"),
    VM_RESTORE("vm_restore", "Total VM's restored"),
    VM_EXPUNGE("vm_expunge", "Total VM's expunged"),
    VNC_CONNECT("vnc_connect", "Total VNC connections created"),
    VNC_DISCONNECT("vnc_disconnect", "Total VNC connections disconnected"),
    NETWORK_CREATE("network_create", "Total networks created"),
    NETWORK_DELETE("network_delete", "Total Networks deleted"),
    NETWORK_UPDATE("network_update", "Total Network updates"),
    FIREWALL_OPEN("firewall_open", "Total firewall rules opened"),
    FIREWALL_CLOSE("firewall_close", "Total firewall rules closed"),
    FIREWALL_EGRESS_OPEN("firewall_egress_open", "Total Firewall egress rules opened"),
    FIREWALL_EGRESS_CLOSE("firewall_egress_close", "Total firewall egress rules closed"),
    LB_CREATE("lb_create", "Total Load balancers created"),
    LB_DELETE("lb_delete", "Total Load balancers deleted"),
    LB_UPDATE("lb_update", "Total number of load balancer updates"),
    LB_ASSIGN_TO_RULE("lb_assign_to_rule", "Total load balancer rules created"),
    LB_REMOVE_FROM_RULE("lb_remove_from_rule", "Total load balancer rules deleted"),

    USER_LOGIN("user_login", "Total users logged in"),
    USER_LOGOUT("user_logout", "Total users logged out"),
    USER_CREATE("user_create", "Total number of users created"),
    USER_DELETE("user_delete", "Total number of users deleted"),
    USER_DISABLE("user_disable", "Total numbers of users disabled"),
    USER_ENABLE("user_enable", "Total number of users enabled"),
    USER_UPDATE("user_update", "Total number of users updated"),
    USER_LOCK("user_lock", "Total number of users locked"),
    REGISTER_SSH_KEYPAIR("register_ssh_keypair", "Total number of ssh key pairs created"),
    DOMAIN_CREATE("domain_create", "Total number of domains created"),
    DOMAIN_DELETE("domain_delete", "Total number of domains deleted"),
    DOMAIN_UPDATE("domain_update", "Total number of domains updated"),
    NIC_CREATE("nic_create", "Total number of NIC's created for VM's"),
    NIC_DELETE("nic_delete", "Total number of NIC's deleted from VM's"),
    NIC_UPDATE("nic_updte", "Total number of NIC's updated for VM's")
    ;

    private String counterName;
    private String description;

    private EventType(String name, String description) {
        this.counterName = name;
        this.description = description;
    }

    public String getCounterName() {
        return this.counterName;
    }

    public String getDescription() {
        return this.description;
    }
}
