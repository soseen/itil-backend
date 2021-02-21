package api.itil.event_management.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
@Setter
@Getter
public class ActiveService {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "primary_sequence", sequenceName = "primary_sequence",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "primary_sequence")
    private Long id;

    @Column(nullable = false, length = 80)
    private String name;

    @Column(nullable = false)
    private Integer priority;

    @OneToMany(mappedBy = "service", targetEntity = EventService.class, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EventService> serviceEventServices;


}
