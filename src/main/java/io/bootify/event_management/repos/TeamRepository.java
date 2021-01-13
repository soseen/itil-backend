package io.bootify.event_management.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import io.bootify.event_management.domain.Team;


public interface TeamRepository extends JpaRepository<Team, Long> {
    // add custom queries here
}
