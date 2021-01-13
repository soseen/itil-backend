package io.bootify.event_management.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import io.bootify.event_management.config.CustomNotFoundException;
import io.bootify.event_management.domain.Team;
import io.bootify.event_management.domain.User;
import io.bootify.event_management.model.UserDTO;
import io.bootify.event_management.repos.TeamRepository;
import io.bootify.event_management.repos.UserRepository;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;

    public UserService(final UserRepository userRepository,
                       final TeamRepository teamRepository) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> mapToDTO(user, new UserDTO()))
                .collect(Collectors.toList());
    }

    public UserDTO get(final Long id) {
        return userRepository.findById(id)
                .map(user -> mapToDTO(user, new UserDTO()))
                .orElseThrow(CustomNotFoundException::new);
    }

    public Long create(final UserDTO userDTO) {
        final User user = new User();
        mapToEntity(userDTO, user);
        return userRepository.save(user).getId();
    }

    public void update(final Long id, final UserDTO userDTO) {
        final User user = userRepository.findById(id)
                .orElseThrow(CustomNotFoundException::new);
        mapToEntity(userDTO, user);
        userRepository.save(user);
    }

    public void delete(final Long id) {
        userRepository.deleteById(id);
    }

    private UserDTO mapToDTO(final User user, final UserDTO userDTO) {
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        userDTO.setTeam(user.getTeam() == null ? null : user.getTeam().getId());
        return userDTO;
    }

    private User mapToEntity(final UserDTO userDTO, final User user) {
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        if (userDTO.getTeam() != null &&
                (user.getTeam() == null || !user.getTeam().getId().equals(userDTO.getTeam()))) {
            final Team team = teamRepository.findById(userDTO.getTeam())
                    .orElseThrow(CustomNotFoundException::new);
            user.setTeam(team);
        }
        return user;
    }

}
