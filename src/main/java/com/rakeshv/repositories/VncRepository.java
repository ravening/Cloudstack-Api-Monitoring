package com.rakeshv.repositories;

import com.rakeshv.models.Vnc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VncRepository extends JpaRepository<Vnc, Long> {
}
