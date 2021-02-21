package api.itil.event_management.rest;

import api.itil.event_management.model.EventDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import api.itil.event_management.service.EventService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping(value = "/api/events", produces = MediaType.APPLICATION_JSON_VALUE)
public class EventController {

    private final EventService eventService;

    public EventController(final EventService eventService) {
        this.eventService = eventService;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping
    public List<EventDTO> getAllEvents() {
        return eventService.findAll();
    }

    @GetMapping("/{id}")
    public EventDTO getEvent(@PathVariable final Long id) {
        return eventService.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createEvent(@RequestBody @Valid final EventDTO eventDTO) {
        return eventService.create(eventDTO);
    }

    @PutMapping("/{id}")
    public void updateEvent(@PathVariable final Long id, @RequestBody @Valid final EventDTO eventDTO) {
        eventService.update(id, eventDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable final Long id) {
        eventService.delete(id);
    }

}
