package io.bootify.event_management.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import io.bootify.event_management.domain.TaskUpdate;


public interface TaskUpdateRepository extends JpaRepository<TaskUpdate, Long> {
    // add custom queries here
}
