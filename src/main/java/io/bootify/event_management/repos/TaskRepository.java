package io.bootify.event_management.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import io.bootify.event_management.domain.Task;


public interface TaskRepository extends JpaRepository<Task, Long> {
    // add custom queries here
}
