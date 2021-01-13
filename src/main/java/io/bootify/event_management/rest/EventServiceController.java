package io.bootify.event_management.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import io.bootify.event_management.model.EventServiceDTO;
import io.bootify.event_management.service.EventServiceService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping(value = "/api/eventServices", produces = MediaType.APPLICATION_JSON_VALUE)
public class EventServiceController {

    private final EventServiceService eventServiceService;

    public EventServiceController(final EventServiceService eventServiceService) {
        this.eventServiceService = eventServiceService;
    }

    @GetMapping
    public List<EventServiceDTO> getAllEventServices() {
        return eventServiceService.findAll();
    }

    @GetMapping("/{id}")
    public EventServiceDTO getEventService(@PathVariable final Long id) {
        return eventServiceService.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createEventService(@RequestBody @Valid final EventServiceDTO eventServiceDTO) {
        return eventServiceService.create(eventServiceDTO);
    }

    @PutMapping("/{id}")
    public void updateEventService(@PathVariable final Long id, @RequestBody @Valid final EventServiceDTO eventServiceDTO) {
        eventServiceService.update(id, eventServiceDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEventService(@PathVariable final Long id) {
        eventServiceService.delete(id);
    }

}
