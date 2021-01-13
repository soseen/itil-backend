package io.bootify.event_management.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
@Setter
@Getter
public class Team {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "primary_sequence", sequenceName = "primary_sequence",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "primary_sequence")
    private Long id;

    @Column(nullable = false, length = 45)
    private String name;

    @OneToMany(mappedBy = "team", targetEntity = Task.class)
    private Set<Task> teamTasks;

    @OneToMany(mappedBy = "team", targetEntity = User.class)
    private Set<User> teamUsers;


}
