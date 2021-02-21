package api.itil.event_management.rest;

import api.itil.event_management.model.RuleDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import api.itil.event_management.service.RuleService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping(value = "/api/rules", produces = MediaType.APPLICATION_JSON_VALUE)
public class RuleController {

    private final RuleService ruleService;

    public RuleController(final RuleService ruleService) {
        this.ruleService = ruleService;
    }

    @GetMapping
    public List<RuleDTO> getAllRules() {
        return ruleService.findAll();
    }

    @GetMapping("/{id}")
    public RuleDTO getRule(@PathVariable final Long id) {
        return ruleService.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createRule(@RequestBody @Valid final RuleDTO ruleDTO) {
        return ruleService.create(ruleDTO);
    }

    @PutMapping("/{id}")
    public void updateRule(@PathVariable final Long id, @RequestBody @Valid final RuleDTO ruleDTO) {
        ruleService.update(id, ruleDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRule(@PathVariable final Long id) {
        ruleService.delete(id);
    }

}
