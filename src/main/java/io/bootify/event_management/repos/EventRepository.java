package io.bootify.event_management.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import io.bootify.event_management.domain.Event;


public interface EventRepository extends JpaRepository<Event, Long> {
    // add custom queries here
}
