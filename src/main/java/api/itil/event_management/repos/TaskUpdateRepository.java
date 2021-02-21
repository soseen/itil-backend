package api.itil.event_management.repos;

import api.itil.event_management.domain.TaskUpdate;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskUpdateRepository extends JpaRepository<TaskUpdate, Long> {
    // add custom queries here
}
