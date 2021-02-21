package api.itil.event_management.repos;

import api.itil.event_management.domain.Rule;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RuleRepository extends JpaRepository<Rule, Long> {
    // add custom queries here
}
