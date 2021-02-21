package api.itil.event_management.rest;

import api.itil.event_management.model.TaskDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import api.itil.event_management.service.TaskService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping(value = "/api/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {

    private final TaskService taskService;

    public TaskController(final TaskService taskService) {
        this.taskService = taskService;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping
    public List<TaskDTO> getAllTasks() {
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public TaskDTO getTask(@PathVariable final Long id) {
        return taskService.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createTask(@RequestBody @Valid final TaskDTO taskDTO) {
        return taskService.create(taskDTO);
    }

    @PutMapping("/{id}")
    public void updateTask(@PathVariable final Long id, @RequestBody @Valid final TaskDTO taskDTO) {
        taskService.update(id, taskDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable final Long id) {
        taskService.delete(id);
    }

}
