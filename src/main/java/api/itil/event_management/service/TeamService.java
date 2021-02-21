package api.itil.event_management.service;

import api.itil.event_management.model.TeamDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import api.itil.event_management.config.CustomNotFoundException;
import api.itil.event_management.domain.Team;
import api.itil.event_management.repos.TeamRepository;


@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(final TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<TeamDTO> findAll() {
        return teamRepository.findAll()
                .stream()
                .map(team -> mapToDTO(team, new TeamDTO()))
                .collect(Collectors.toList());
    }

    public TeamDTO get(final Long id) {
        return teamRepository.findById(id)
                .map(team -> mapToDTO(team, new TeamDTO()))
                .orElseThrow(CustomNotFoundException::new);
    }

    public Long create(final TeamDTO teamDTO) {
        final Team team = new Team();
        mapToEntity(teamDTO, team);
        return teamRepository.save(team).getId();
    }

    public void update(final Long id, final TeamDTO teamDTO) {
        final Team team = teamRepository.findById(id)
                .orElseThrow(CustomNotFoundException::new);
        mapToEntity(teamDTO, team);
        teamRepository.save(team);
    }

    public void delete(final Long id) {
        teamRepository.deleteById(id);
    }

    private TeamDTO mapToDTO(final Team team, final TeamDTO teamDTO) {
        teamDTO.setId(team.getId());
        teamDTO.setName(team.getName());
        return teamDTO;
    }

    private Team mapToEntity(final TeamDTO teamDTO, final Team team) {
        team.setName(teamDTO.getName());
        return team;
    }

}
