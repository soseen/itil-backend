package io.bootify.event_management.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import io.bootify.event_management.domain.ActiveService;


public interface ActiveServiceRepository extends JpaRepository<ActiveService, Long> {
    // add custom queries here
}
