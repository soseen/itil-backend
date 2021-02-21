package api.itil.event_management.repos;

import api.itil.event_management.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TeamRepository extends JpaRepository<Team, Long> {
    // add custom queries here
}
