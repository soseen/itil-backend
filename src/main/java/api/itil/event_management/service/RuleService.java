package api.itil.event_management.service;

import api.itil.event_management.model.RuleDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import api.itil.event_management.config.CustomNotFoundException;
import api.itil.event_management.domain.Rule;
import api.itil.event_management.repos.RuleRepository;


@Service
public class RuleService {

    private final RuleRepository ruleRepository;

    public RuleService(final RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    public List<RuleDTO> findAll() {
        return ruleRepository.findAll()
                .stream()
                .map(rule -> mapToDTO(rule, new RuleDTO()))
                .collect(Collectors.toList());
    }

    public RuleDTO get(final Long id) {
        return ruleRepository.findById(id)
                .map(rule -> mapToDTO(rule, new RuleDTO()))
                .orElseThrow(CustomNotFoundException::new);
    }

    public Long create(final RuleDTO ruleDTO) {
        final Rule rule = new Rule();
        mapToEntity(ruleDTO, rule);
        return ruleRepository.save(rule).getId();
    }

    public void update(final Long id, final RuleDTO ruleDTO) {
        final Rule rule = ruleRepository.findById(id)
                .orElseThrow(CustomNotFoundException::new);
        mapToEntity(ruleDTO, rule);
        ruleRepository.save(rule);
    }

    public void delete(final Long id) {
        ruleRepository.deleteById(id);
    }

    private RuleDTO mapToDTO(final Rule rule, final RuleDTO ruleDTO) {
        ruleDTO.setId(rule.getId());
        ruleDTO.setName(rule.getName());
        ruleDTO.setSeverity(rule.getSeverity());
        ruleDTO.setPriority(rule.getPriority());
        ruleDTO.setAttribute(rule.getAttribute());
        ruleDTO.setOperator(rule.getOperator());
        ruleDTO.setValue(rule.getValue());
        ruleDTO.setDate(rule.getDate());
        return ruleDTO;
    }

    private Rule mapToEntity(final RuleDTO ruleDTO, final Rule rule) {
        rule.setName(ruleDTO.getName());
        rule.setSeverity(ruleDTO.getSeverity());
        rule.setPriority(ruleDTO.getPriority());
        rule.setAttribute(ruleDTO.getAttribute());
        rule.setOperator(ruleDTO.getOperator());
        rule.setValue(ruleDTO.getValue());
        rule.setDate(ruleDTO.getDate());
        return rule;
    }

}
