package com.booking.optimizer.repository;

import com.booking.optimizer.model.entity.CustomerSegmentLimit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerSegmentLimitRepository extends CrudRepository<CustomerSegmentLimit,Long> {
}
