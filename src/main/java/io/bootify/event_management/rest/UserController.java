package io.bootify.event_management.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import io.bootify.event_management.model.UserDTO;
import io.bootify.event_management.service.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable final Long id) {
        return userService.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createUser(@RequestBody @Valid final UserDTO userDTO) {
        return userService.create(userDTO);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable final Long id, @RequestBody @Valid final UserDTO userDTO) {
        userService.update(id, userDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable final Long id) {
        userService.delete(id);
    }

}
