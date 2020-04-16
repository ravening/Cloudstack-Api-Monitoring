package com.rakeshv.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Vm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int vmCreate;
    private int vmDestroy;
    private int vmStart;
    private int vmStop;
    private int vmReboot;
    private int vmUpdate;
    private int vmUpgrade;
    private int vmResetPassword;
    private int vmResetsshkey;
    private int vmResetsecuritygroups;
    private int vmMigrate;
    private int vmCancelMigration;
    private int vmRestore;
    private int vmExpunge;
}
