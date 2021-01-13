package io.bootify.event_management.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import io.bootify.event_management.config.CustomNotFoundException;
import io.bootify.event_management.domain.ActiveService;
import io.bootify.event_management.model.ActiveServiceDTO;
import io.bootify.event_management.repos.ActiveServiceRepository;


@Service
public class ActiveServiceService {

    private final ActiveServiceRepository activeServiceRepository;

    public ActiveServiceService(final ActiveServiceRepository activeServiceRepository) {
        this.activeServiceRepository = activeServiceRepository;
    }

    public List<ActiveServiceDTO> findAll() {
        return activeServiceRepository.findAll()
                .stream()
                .map(activeService -> mapToDTO(activeService, new ActiveServiceDTO()))
                .collect(Collectors.toList());
    }

    public ActiveServiceDTO get(final Long id) {
        return activeServiceRepository.findById(id)
                .map(activeService -> mapToDTO(activeService, new ActiveServiceDTO()))
                .orElseThrow(CustomNotFoundException::new);
    }

    public Long create(final ActiveServiceDTO activeServiceDTO) {
        final ActiveService activeService = new ActiveService();
        mapToEntity(activeServiceDTO, activeService);
        return activeServiceRepository.save(activeService).getId();
    }

    public void update(final Long id, final ActiveServiceDTO activeServiceDTO) {
        final ActiveService activeService = activeServiceRepository.findById(id)
                .orElseThrow(CustomNotFoundException::new);
        mapToEntity(activeServiceDTO, activeService);
        activeServiceRepository.save(activeService);
    }

    public void delete(final Long id) {
        activeServiceRepository.deleteById(id);
    }

    private ActiveServiceDTO mapToDTO(final ActiveService activeService, final ActiveServiceDTO activeServiceDTO) {
        activeServiceDTO.setId(activeService.getId());
        activeServiceDTO.setName(activeService.getName());
        activeServiceDTO.setPriority(activeService.getPriority());
        return activeServiceDTO;
    }

    private ActiveService mapToEntity(final ActiveServiceDTO activeServiceDTO, final ActiveService activeService) {
        activeService.setName(activeServiceDTO.getName());
        activeService.setPriority(activeServiceDTO.getPriority());
        return activeService;
    }

}
