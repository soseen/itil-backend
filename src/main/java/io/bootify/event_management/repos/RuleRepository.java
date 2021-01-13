package io.bootify.event_management.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import io.bootify.event_management.domain.Rule;


public interface RuleRepository extends JpaRepository<Rule, Long> {
    // add custom queries here
}
