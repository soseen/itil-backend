package io.bootify.event_management.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import io.bootify.event_management.config.CustomNotFoundException;
import io.bootify.event_management.domain.ActiveService;
import io.bootify.event_management.domain.Event;
import io.bootify.event_management.domain.EventService;
import io.bootify.event_management.model.EventServiceDTO;
import io.bootify.event_management.repos.ActiveServiceRepository;
import io.bootify.event_management.repos.EventRepository;
import io.bootify.event_management.repos.EventServiceRepository;


@Service
public class EventServiceService {

    private final EventServiceRepository eventServiceRepository;
    private final EventRepository eventRepository;
    private final ActiveServiceRepository activeServiceRepository;

    public EventServiceService(final EventServiceRepository eventServiceRepository,
                               final EventRepository eventRepository,
                               final ActiveServiceRepository activeServiceRepository) {
        this.eventServiceRepository = eventServiceRepository;
        this.eventRepository = eventRepository;
        this.activeServiceRepository = activeServiceRepository;
    }

    public List<EventServiceDTO> findAll() {
        return eventServiceRepository.findAll()
                .stream()
                .map(eventService -> mapToDTO(eventService, new EventServiceDTO()))
                .collect(Collectors.toList());
    }

    public EventServiceDTO get(final Long id) {
        return eventServiceRepository.findById(id)
                .map(eventService -> mapToDTO(eventService, new EventServiceDTO()))
                .orElseThrow(CustomNotFoundException::new);
    }

    public Long create(final EventServiceDTO eventServiceDTO) {
        final EventService eventService = new EventService();
        mapToEntity(eventServiceDTO, eventService);
        return eventServiceRepository.save(eventService).getId();
    }

    public void update(final Long id, final EventServiceDTO eventServiceDTO) {
        final EventService eventService = eventServiceRepository.findById(id)
                .orElseThrow(CustomNotFoundException::new);
        mapToEntity(eventServiceDTO, eventService);
        eventServiceRepository.save(eventService);
    }

    public void delete(final Long id) {
        eventServiceRepository.deleteById(id);
    }

    private EventServiceDTO mapToDTO(final EventService eventService, final EventServiceDTO eventServiceDTO) {
        eventServiceDTO.setId(eventService.getId());
        eventServiceDTO.setEvent(eventService.getEvent() == null ? null : eventService.getEvent().getId());
        eventServiceDTO.setService(eventService.getService() == null ? null : eventService.getService().getId());
        return eventServiceDTO;
    }

    private EventService mapToEntity(final EventServiceDTO eventServiceDTO, final EventService eventService) {
        if (eventServiceDTO.getEvent() != null &&
                (eventService.getEvent() == null || !eventService.getEvent().getId().equals(eventServiceDTO.getEvent()))) {
            final Event event = eventRepository.findById(eventServiceDTO.getEvent())
                    .orElseThrow(CustomNotFoundException::new);
            eventService.setEvent(event);
        }
        if (eventServiceDTO.getService() != null &&
                (eventService.getService() == null || !eventService.getService().getId().equals(eventServiceDTO.getService()))) {
            final ActiveService service = activeServiceRepository.findById(eventServiceDTO.getService())
                    .orElseThrow(CustomNotFoundException::new);
            eventService.setService(service);
        }
        return eventService;
    }

}
