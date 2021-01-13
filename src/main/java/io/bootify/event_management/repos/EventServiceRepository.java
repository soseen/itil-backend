package io.bootify.event_management.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import io.bootify.event_management.domain.EventService;


public interface EventServiceRepository extends JpaRepository<EventService, Long> {
    // add custom queries here
}
