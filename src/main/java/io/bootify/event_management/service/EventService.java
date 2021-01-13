package io.bootify.event_management.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import io.bootify.event_management.config.CustomNotFoundException;
import io.bootify.event_management.domain.Event;
import io.bootify.event_management.model.EventDTO;
import io.bootify.event_management.repos.EventRepository;


@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(final EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<EventDTO> findAll() {
        return eventRepository.findAll()
                .stream()
                .map(event -> mapToDTO(event, new EventDTO()))
                .collect(Collectors.toList());
    }

    public EventDTO get(final Long id) {
        return eventRepository.findById(id)
                .map(event -> mapToDTO(event, new EventDTO()))
                .orElseThrow(CustomNotFoundException::new);
    }

    public Long create(final EventDTO eventDTO) {
        final Event event = new Event();
        mapToEntity(eventDTO, event);
        return eventRepository.save(event).getId();
    }

    public void update(final Long id, final EventDTO eventDTO) {
        final Event event = eventRepository.findById(id)
                .orElseThrow(CustomNotFoundException::new);
        mapToEntity(eventDTO, event);
        eventRepository.save(event);
    }

    public void delete(final Long id) {
        eventRepository.deleteById(id);
    }

    private EventDTO mapToDTO(final Event event, final EventDTO eventDTO) {
        eventDTO.setId(event.getId());
        eventDTO.setName(event.getName());
        eventDTO.setSeverity(event.getSeverity());
        eventDTO.setSource(event.getSource());
        eventDTO.setStartDate(event.getStartDate());
        eventDTO.setEndDate(event.getEndDate());
        eventDTO.setResolved(event.getResolved());
        return eventDTO;
    }

    private Event mapToEntity(final EventDTO eventDTO, final Event event) {
        event.setName(eventDTO.getName());
        event.setSeverity(eventDTO.getSeverity());
        event.setSource(eventDTO.getSource());
        event.setStartDate(eventDTO.getStartDate());
        event.setEndDate(eventDTO.getEndDate());
        event.setResolved(eventDTO.getResolved());
        return event;
    }

}
