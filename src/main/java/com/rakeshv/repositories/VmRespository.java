package com.rakeshv.repositories;

import com.rakeshv.models.Vm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VmRespository extends JpaRepository<Vm, Integer> {
}
