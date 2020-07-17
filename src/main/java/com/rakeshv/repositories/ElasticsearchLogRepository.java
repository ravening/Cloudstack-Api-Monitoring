package com.rakeshv.repositories;

import com.rakeshv.models.ElasticsearchLog;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElasticsearchLogRepository extends ReactiveCrudRepository<ElasticsearchLog, Long> {
}
