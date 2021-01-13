package io.bootify.event_management.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import io.bootify.event_management.model.ActiveServiceDTO;
import io.bootify.event_management.service.ActiveServiceService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping(value = "/api/activeServices", produces = MediaType.APPLICATION_JSON_VALUE)
public class ActiveServiceController {

    private final ActiveServiceService activeServiceService;

    public ActiveServiceController(final ActiveServiceService activeServiceService) {
        this.activeServiceService = activeServiceService;
    }

    @GetMapping
    public List<ActiveServiceDTO> getAllActiveServices() {
        return activeServiceService.findAll();
    }

    @GetMapping("/{id}")
    public ActiveServiceDTO getActiveService(@PathVariable final Long id) {
        return activeServiceService.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createActiveService(@RequestBody @Valid final ActiveServiceDTO activeServiceDTO) {
        return activeServiceService.create(activeServiceDTO);
    }

    @PutMapping("/{id}")
    public void updateActiveService(@PathVariable final Long id, @RequestBody @Valid final ActiveServiceDTO activeServiceDTO) {
        activeServiceService.update(id, activeServiceDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteActiveService(@PathVariable final Long id) {
        activeServiceService.delete(id);
    }

}
