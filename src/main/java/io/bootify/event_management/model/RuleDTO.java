package io.bootify.event_management.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
public class RuleDTO {

    private Long id;

    @NotNull
    @Size(max = 80)
    private String name;

    @NotNull
    @Size(max = 15)
    private String severity;

    @NotNull
    private Integer priority;

    @NotNull
    @Size(max = 30)
    private String attribute;

    @NotNull
    @Size(max = 5)
    private String operator;

    @NotNull
    @Size(max = 30)
    private String value;

    @NotNull
    private LocalDate date;

}
