package io.bootify.event_management.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan(basePackages = {"io.bootify.event_management.domain"})
@EnableJpaRepositories(basePackages = {"io.bootify.event_management.repos"})
@EnableTransactionManagement
public class DomainConfig {
}
