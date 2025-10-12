package org.masd.tripservice.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.masd.tripservice.model.TripRequestDTO;
import org.masd.tripservice.service.TripRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/tripRequests", produces = MediaType.APPLICATION_JSON_VALUE)
public class TripRequestResource {

    private final TripRequestService tripRequestService;

    public TripRequestResource(final TripRequestService tripRequestService) {
        this.tripRequestService = tripRequestService;
    }

    @GetMapping
    public ResponseEntity<List<TripRequestDTO>> getAllTripRequests() {
        return ResponseEntity.ok(tripRequestService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripRequestDTO> getTripRequest(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(tripRequestService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createTripRequest(
            @RequestBody @Valid final TripRequestDTO tripRequestDTO) {
        final Long createdId = tripRequestService.create(tripRequestDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateTripRequest(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final TripRequestDTO tripRequestDTO) {
        tripRequestService.update(id, tripRequestDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteTripRequest(@PathVariable(name = "id") final Long id) {
        tripRequestService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
