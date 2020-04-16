package com.rakeshv.repositories;

import com.rakeshv.models.SecurityGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityGroupRepository extends JpaRepository<SecurityGroup, Long> {
}
