package org.masd.configserver;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/config")
public class ConfigController {

    private final ConfigRepository repo;
    private final ConfigMonitorService service;
    public ConfigController(ConfigRepository repo, ConfigMonitorService service) {
        this.repo = repo;
        this.service = service;
    }

    @PostMapping
    public ConfigDocument create(@RequestBody ConfigDocument doc) {
        return repo.save(doc);
    }

    @PutMapping("/{id}")
    public ConfigDocument update(@PathVariable String id, @RequestBody ConfigDocument doc) {
        doc.setId(id);
        return repo.save(doc);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repo.deleteById(id);
    }

    @GetMapping("/all")
    public List<ConfigPropertyDto> getAllConfigProperties() {
        return service.getAllProperties();
    }
}
