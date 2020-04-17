package com.rakeshv.repositories;

import com.rakeshv.models.ApiCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApiCountRepository extends JpaRepository<ApiCount, Long> {
    Optional<ApiCount> findByDomainNameContains(String domain);
}
