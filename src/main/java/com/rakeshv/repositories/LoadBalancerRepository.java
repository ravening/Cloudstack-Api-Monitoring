package com.rakeshv.repositories;

import com.rakeshv.models.LoadBalancer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadBalancerRepository extends JpaRepository<LoadBalancer, Long> {
}
