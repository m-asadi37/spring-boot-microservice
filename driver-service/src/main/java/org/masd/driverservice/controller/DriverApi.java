package org.masd.driverservice.controller;

import jakarta.validation.Valid;
import org.masd.driverservice.model.DriverDto;
import org.masd.driverservice.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
public class DriverApi {

    private final DriverService driverService;

    @Autowired
    public DriverApi(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping
    public ResponseEntity<DriverDto> createDriver(@Valid @RequestBody DriverDto request) {
        DriverDto dto = driverService.createDriver(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverDto> getDriver(@PathVariable Long id) {
        return ResponseEntity.ok(driverService.getDriver(id));
    }

    @GetMapping("/available")
    public ResponseEntity<List<DriverDto>> getAvailableDrivers() {
        return ResponseEntity.ok(driverService.getAvailableDrivers());
    }

    @PostMapping("/{id}/status")
    public ResponseEntity<DriverDto> updateStatus(@PathVariable Long id,
                                                  @RequestBody String driverStatus) {
        return ResponseEntity.ok(driverService.updateStatus(id, driverStatus));
    }
}
