package com.rakeshv.repositories;

import com.rakeshv.models.Nic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NicRepository extends JpaRepository<Nic, Long> {
}
