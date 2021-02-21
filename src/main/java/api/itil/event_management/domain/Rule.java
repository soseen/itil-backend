package api.itil.event_management.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Setter
@Getter
public class Rule {

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

    @Column(nullable = false)
    private Integer priority;

    @Column(nullable = false, length = 30)
    private String attribute;

    @Column(nullable = false, length = 5)
    private String operator;

    @Column(nullable = false, length = 30)
    private String value;

    @Column(nullable = false)
    private LocalDate date;


}
