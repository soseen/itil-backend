package api.itil.event_management.service;

import api.itil.event_management.model.TaskUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import api.itil.event_management.config.CustomNotFoundException;
import api.itil.event_management.domain.Task;
import api.itil.event_management.domain.TaskUpdate;
import api.itil.event_management.repos.TaskRepository;
import api.itil.event_management.repos.TaskUpdateRepository;


@Service
public class TaskUpdateService {

    private final TaskUpdateRepository taskUpdateRepository;
    private final TaskRepository taskRepository;

    public TaskUpdateService(final TaskUpdateRepository taskUpdateRepository,
                             final TaskRepository taskRepository) {
        this.taskUpdateRepository = taskUpdateRepository;
        this.taskRepository = taskRepository;
    }

    public List<TaskUpdateDTO> findAll() {
        return taskUpdateRepository.findAll()
                .stream()
                .map(taskUpdate -> mapToDTO(taskUpdate, new TaskUpdateDTO()))
                .collect(Collectors.toList());
    }

    public TaskUpdateDTO get(final Long id) {
        return taskUpdateRepository.findById(id)
                .map(taskUpdate -> mapToDTO(taskUpdate, new TaskUpdateDTO()))
                .orElseThrow(CustomNotFoundException::new);
    }

    public Long create(final TaskUpdateDTO taskUpdateDTO) {
        final TaskUpdate taskUpdate = new TaskUpdate();
        mapToEntity(taskUpdateDTO, taskUpdate);
        return taskUpdateRepository.save(taskUpdate).getId();
    }

    public void update(final Long id, final TaskUpdateDTO taskUpdateDTO) {
        final TaskUpdate taskUpdate = taskUpdateRepository.findById(id)
                .orElseThrow(CustomNotFoundException::new);
        mapToEntity(taskUpdateDTO, taskUpdate);
        taskUpdateRepository.save(taskUpdate);
    }

    public void delete(final Long id) {
        taskUpdateRepository.deleteById(id);
    }

    private TaskUpdateDTO mapToDTO(final TaskUpdate taskUpdate, final TaskUpdateDTO taskUpdateDTO) {
        taskUpdateDTO.setId(taskUpdate.getId());
        taskUpdateDTO.setDescription(taskUpdate.getDescription());
        taskUpdateDTO.setDate(taskUpdate.getDate());
        taskUpdateDTO.setStatus(taskUpdate.getStatus());
        taskUpdateDTO.setTask(taskUpdate.getTask() == null ? null : taskUpdate.getTask().getId());
        return taskUpdateDTO;
    }

    private TaskUpdate mapToEntity(final TaskUpdateDTO taskUpdateDTO, final TaskUpdate taskUpdate) {
        taskUpdate.setDescription(taskUpdateDTO.getDescription());
        taskUpdate.setDate(taskUpdateDTO.getDate());
        taskUpdate.setStatus(taskUpdateDTO.getStatus());
        if (taskUpdateDTO.getTask() != null &&
                (taskUpdate.getTask() == null || !taskUpdate.getTask().getId().equals(taskUpdateDTO.getTask()))) {
            final Task task = taskRepository.findById(taskUpdateDTO.getTask())
                    .orElseThrow(CustomNotFoundException::new);
            taskUpdate.setTask(task);
        }
        return taskUpdate;
    }

}
