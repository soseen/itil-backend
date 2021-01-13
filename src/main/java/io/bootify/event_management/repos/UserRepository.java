package io.bootify.event_management.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import io.bootify.event_management.domain.User;


public interface UserRepository extends JpaRepository<User, Long> {
    // add custom queries here
}
