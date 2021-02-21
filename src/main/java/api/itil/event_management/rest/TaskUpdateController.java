package api.itil.event_management.rest;

import api.itil.event_management.model.TaskUpdateDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import api.itil.event_management.service.TaskUpdateService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping(value = "/api/taskUpdates", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskUpdateController {

    private final TaskUpdateService taskUpdateService;

    public TaskUpdateController(final TaskUpdateService taskUpdateService) {
        this.taskUpdateService = taskUpdateService;
    }

    @GetMapping
    public List<TaskUpdateDTO> getAllTaskUpdates() {
        return taskUpdateService.findAll();
    }

    @GetMapping("/{id}")
    public TaskUpdateDTO getTaskUpdate(@PathVariable final Long id) {
        return taskUpdateService.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createTaskUpdate(@RequestBody @Valid final TaskUpdateDTO taskUpdateDTO) {
        return taskUpdateService.create(taskUpdateDTO);
    }

    @PutMapping("/{id}")
    public void updateTaskUpdate(@PathVariable final Long id, @RequestBody @Valid final TaskUpdateDTO taskUpdateDTO) {
        taskUpdateService.update(id, taskUpdateDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTaskUpdate(@PathVariable final Long id) {
        taskUpdateService.delete(id);
    }

}
