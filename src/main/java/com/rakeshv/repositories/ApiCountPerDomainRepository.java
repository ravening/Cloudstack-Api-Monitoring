package com.rakeshv.repositories;

import com.rakeshv.models.ApiCountPerDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApiCountPerDomainRepository extends JpaRepository<ApiCountPerDomain, Long> {
    Optional<ApiCountPerDomain> findByDomainNameEqualsAndApiEquals(String domain, String api);
}
