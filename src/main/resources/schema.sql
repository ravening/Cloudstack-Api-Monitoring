CREATE DATABASE IF NOT EXISTS cloudstack_stats;
USE cloudstack_stats;

DROP TABLE IF EXISTS vm;
CREATE TABLE vm(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    vm_create INTEGER,
    vm_destroy INTEGER,
    vm_start INTEGER,
    vm_stop INTEGER,
    vm_reboot INTEGER,
    vm_update INTEGER,
    vm_upgrade INTEGER,
    vm_reset_password INTEGER,
    vm_resetsshkey INTEGER,
    vm_resetsecuritygroups INTEGER,
    vm_migrate INTEGER,
    vm_cancel_migration INTEGER,
    vm_restore INTEGER,
    vm_expunge INTEGER
);

DROP TABLE IF EXISTS user;
CREATE TABLE user(
    id integer auto_increment primary key,
    user_login integer,
    user_logout integer,
    user_create integer,
    user_delete integer,
    user_disable integer,
    user_enable integer,
    user_update integer,
    user_lock integer
);

DROP TABLE IF EXISTS nic;
CREATE TABLE nic(
    id integer auto_increment primary key,
    nic_create integer,
    nic_delete integer,
    nic_update integer
);

DROP TABLE IF EXISTS load_balancer;
CREATE TABLE load_balancer(
    id integer auto_increment primary key,
    lb_create integer,
    lb_delete integer,
    lb_update integer,
    lb_cert_upload integer,
    lb_cert_delete integer,
    lb_cert_assign integer,
    lb_cert_remove integer,
    lb_assign_to_rule integer,
    lb_remove_from_rule integer
);

DROP TABLE IF EXISTS volume;
CREATE TABLE volume(
    id INT(20) AUTO_INCREMENT PRIMARY KEY,
    volume_create INT(20),
    volume_delete INT(20),
    volume_attach INT(20),
    volume_detach INT(20),
    volume_extract INT(20),
    volume_upload INT(20),
    volume_migrate INT(20),
    volume_resize INT(20),
    volume_update INT(20),
    volume_destroy INT(20)
);

DROP TABLE IF EXISTS domain;
CREATE TABLE domain(
    id INT(20) AUTO_INCREMENT PRIMARY KEY,
    domain_create INT(20),
    domain_delete INT(20),
    domain_update INT(20)
);

DROP TABLE IF EXISTS template;
CREATE TABLE template(
    id INT(20) AUTO_INCREMENT PRIMARY KEY,
    template_create INT(20),
    template_delete INT(20),
    template_update INT(20),
    template_download_start INT(20),
    template_download_success INT(20),
    template_download_failed INT(20),
    template_copy INT(20),
    template_extract INT(20),
    template_upload INT(20),
    template_cleanup INT(20)
);

DROP TABLE IF EXISTS account;
CREATE TABLE account(
    id integer auto_increment primary key,
    account_create integer,
    account_delete integer,
    account_enable integer,
    account_disable integer,
    account_update integer
);

DROP TABLE IF EXISTS vpn;
CREATE TABLE vpn(
    id INT(20) AUTO_INCREMENT PRIMARY KEY,
    remote_access_create INT(20),
    remote_access_destroy INT(20),
    remote_access_update INT(20),
    user_add INT(20),
    user_remove INT(20),
    s2s_vpn_gateway_create INT(20),
    s2s_vpn_gateway_delete INT(20),
    s2s_vpn_gateway_update INT(20),
    s2s_customer_gateway_create INT(20),
    s2s_customer_gateway_delete INT(20),
    s2s_customer_gateway_update INT(20),
    s2s_connection_create INT(20),
    s2s_connection_delete INT(20),
    s2s_connection_reset INT(20),
    s2s_connection_update INT(20)
);

DROP TABLE IF EXISTS security_group;
CREATE TABLE security_group(
    id integer auto_increment primary key,
    sg_create INT(20),
    sg_delete INT(20),
    sg_assign INT(20),
    sg_remove INT(20),
    sg_update INT(20),
    sg_auth_ingress INT(20),
    sg_revoke_ingress INT(20),
    sg_auth_egress INT(20),
    sg_revoke_egress INT(20)
);

DROP TABLE IF EXISTS network;
CREATE TABLE network(
    id INT(20) AUTO_INCREMENT PRIMARY KEY,
    network_create INT(20),
    network_delete INT(20),
    network_update INT(20),
    network_migrate INT(20),
    network_restart INT(20),
    network_acl_create INT(20),
    network_acl_delete INT(20),
    network_acl_replace INT(20),
    network_acl_update INT(20),
    network_acl_item_create INT(20),
    network_acl_item_update INT(20),
    network_acl_item_delete INT(20),
    network_element_configure INT(20),
    network_offering_create INT(20),
    network_offering_assign INT(20),
    network_offering_edit INT(20),
    network_offering_remove INT(20),
    network_offering_delete INT(20)
);

DROP TABLE IF EXISTS api_count;
CREATE TABLE api_count(
    id integer auto_increment primary key,
    domain_name varchar(20),
    count INT(20)
);

DROP TABLE IF EXISTS iso;
CREATE TABLE iso(
    id integer auto_increment primary key,
    iso_create INT(20),
    iso_delete INT(20),
    iso_copy INT(20),
    iso_attach INT(20),
    iso_detach INT(20)
    iso_extract INT(20)
    iso_upload INT(20)
);
