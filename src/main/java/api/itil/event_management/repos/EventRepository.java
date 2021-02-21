package api.itil.event_management.repos;

import api.itil.event_management.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EventRepository extends JpaRepository<Event, Long> {
    // add custom queries here
}
