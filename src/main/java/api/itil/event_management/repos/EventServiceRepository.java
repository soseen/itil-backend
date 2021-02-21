package api.itil.event_management.repos;

import api.itil.event_management.domain.EventService;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EventServiceRepository extends JpaRepository<EventService, Long> {
    // add custom queries here
}
