package com.rakeshv.repositories;

import com.rakeshv.models.Vpn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VpnRepository extends JpaRepository<Vpn, Long> {
}
