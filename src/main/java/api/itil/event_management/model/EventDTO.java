package api.itil.event_management.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
public class EventDTO {

    private Long id;

    @NotNull
    @Size(max = 80)
    private String name;

    @NotNull
    @Size(max = 15)
    private String severity;

    @NotNull
    @Size(max = 30)
    private String source;

    @NotNull
    private LocalDate startDate;

    private LocalDate endDate;

    private Boolean resolved;

}
