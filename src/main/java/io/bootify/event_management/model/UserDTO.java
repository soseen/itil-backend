package io.bootify.event_management.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
public class UserDTO {

    private Long id;

    @NotNull
    @Size(max = 45)
    private String username;

    @Size(max = 15)
    private String password;

    @NotNull
    @Size(max = 30)
    private String role;

    private Long team;

}
