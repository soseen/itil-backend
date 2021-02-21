package api.itil.event_management.rest;

import api.itil.event_management.model.TeamDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import api.itil.event_management.service.TeamService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping(value = "/api/teams", produces = MediaType.APPLICATION_JSON_VALUE)
public class TeamController {

    private final TeamService teamService;

    public TeamController(final TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<TeamDTO> getAllTeams() {
        return teamService.findAll();
    }

    @GetMapping("/{id}")
    public TeamDTO getTeam(@PathVariable final Long id) {
        return teamService.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createTeam(@RequestBody @Valid final TeamDTO teamDTO) {
        return teamService.create(teamDTO);
    }

    @PutMapping("/{id}")
    public void updateTeam(@PathVariable final Long id, @RequestBody @Valid final TeamDTO teamDTO) {
        teamService.update(id, teamDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTeam(@PathVariable final Long id) {
        teamService.delete(id);
    }

}
