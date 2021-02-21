package api.itil.event_management.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
public class TaskUpdateDTO {

    private Long id;

    @NotNull
    @Size(max = 80)
    private String description;

    @NotNull
    private LocalDate date;

    @NotNull
    private Integer status;

    private Long task;

}
