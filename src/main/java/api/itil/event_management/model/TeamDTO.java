package api.itil.event_management.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
public class TeamDTO {

    private Long id;

    @NotNull
    @Size(max = 45)
    private String name;

}
