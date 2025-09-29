package org.masd.passenger.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.masd.passenger.model.PassengerDTO;
import org.masd.passenger.service.PassengerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/passenger", produces = MediaType.APPLICATION_JSON_VALUE)
public class PassengerApi {

    private final PassengerService passengerService;

    public PassengerApi(final PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping
    public ResponseEntity<List<PassengerDTO>> getAllPassengers() {
        return ResponseEntity.ok(passengerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PassengerDTO> getPassenger(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(passengerService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createPassenger(
            @RequestBody @Valid final PassengerDTO passengerDTO) {
        final Long createdId = passengerService.create(passengerDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deletePassenger(@PathVariable(name = "id") final Long id) {
        passengerService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
