package api.itil.event_management.repos;

import api.itil.event_management.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task, Long> {
    // add custom queries here
}
