package io.bootify.event_management.model;

import io.bootify.event_management.domain.Event;
import io.bootify.event_management.domain.Team;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
public class TaskDTOResponse {

    private Long id;

    @NotNull
    @Size(max = 80)
    private String name;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private Boolean closed;

    private Team team;

    private Event event;
}
