package io.bootify.event_management.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;


@Entity
@Setter
@Getter
public class Event {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "primary_sequence", sequenceName = "primary_sequence",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "primary_sequence")
    private Long id;

    @Column(nullable = false, length = 80)
    private String name;

    @Column(nullable = false, length = 15)
    private String severity;

    @Column(nullable = false, length = 30)
    private String source;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Column
    private Boolean resolved;

    @OneToMany(mappedBy = "event", targetEntity = EventService.class, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EventService> eventEventServices;

    @OneToMany(mappedBy = "event", targetEntity = Task.class, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Task> eventTasks;


}
