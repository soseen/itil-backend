package api.itil.event_management.repos;

import api.itil.event_management.domain.ActiveService;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ActiveServiceRepository extends JpaRepository<ActiveService, Long> {
    // add custom queries here
}
