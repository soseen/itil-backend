package io.bootify.event_management.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import io.bootify.event_management.config.CustomNotFoundException;
import io.bootify.event_management.domain.Event;
import io.bootify.event_management.domain.Task;
import io.bootify.event_management.domain.Team;
import io.bootify.event_management.model.TaskDTO;
import io.bootify.event_management.repos.EventRepository;
import io.bootify.event_management.repos.TaskRepository;
import io.bootify.event_management.repos.TeamRepository;


@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TeamRepository teamRepository;
    private final EventRepository eventRepository;

    public TaskService(final TaskRepository taskRepository,
                       final TeamRepository teamRepository,
                       final EventRepository eventRepository) {
        this.taskRepository = taskRepository;
        this.teamRepository = teamRepository;
        this.eventRepository = eventRepository;
    }

    public List<TaskDTO> findAll() {
        return taskRepository.findAll()
                .stream()
                .map(task -> mapToDTO(task, new TaskDTO()))
                .collect(Collectors.toList());
    }

    public TaskDTO get(final Long id) {
        return taskRepository.findById(id)
                .map(task -> mapToDTO(task, new TaskDTO()))
                .orElseThrow(CustomNotFoundException::new);
    }

    public Long create(final TaskDTO taskDTO) {
        final Task task = new Task();
        mapToEntity(taskDTO, task);
        return taskRepository.save(task).getId();
    }

    public void update(final Long id, final TaskDTO taskDTO) {
        final Task task = taskRepository.findById(id)
                .orElseThrow(CustomNotFoundException::new);
        mapToEntity(taskDTO, task);
        taskRepository.save(task);
    }

    public void delete(final Long id) {
        taskRepository.deleteById(id);
    }

    private TaskDTO mapToDTO(final Task task, final TaskDTO taskDTO) {
        taskDTO.setId(task.getId());
        taskDTO.setName(task.getName());
        taskDTO.setStartDate(task.getStartDate());
        taskDTO.setClosed(task.getClosed());
        taskDTO.setTeam(task.getTeam() == null ? null : task.getTeam().getId());
        taskDTO.setEvent(task.getEvent() == null ? null : task.getEvent().getId());
        return taskDTO;
    }

    private Task mapToEntity(final TaskDTO taskDTO, final Task task) {
        task.setName(taskDTO.getName());
        task.setStartDate(taskDTO.getStartDate());
        task.setClosed(taskDTO.getClosed());
        if (taskDTO.getTeam() != null &&
                (task.getTeam() == null || !task.getTeam().getId().equals(taskDTO.getTeam()))) {
            final Team team = teamRepository.findById(taskDTO.getTeam())
                    .orElseThrow(CustomNotFoundException::new);
            task.setTeam(team);
        }
        if (taskDTO.getEvent() != null &&
                (task.getEvent() == null || !task.getEvent().getId().equals(taskDTO.getEvent()))) {
            final Event event = eventRepository.findById(taskDTO.getEvent())
                    .orElseThrow(CustomNotFoundException::new);
            task.setEvent(event);
        }
        return task;
    }

}
