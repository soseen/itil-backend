package api.itil.event_management.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
public class TaskDTO {

    private Long id;

    @NotNull
    @Size(max = 80)
    private String name;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private Boolean closed;

    private Long team;

    private Long event;

}
