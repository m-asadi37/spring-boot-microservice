package org.masd.passenger.service;

import org.masd.passenger.domain.Passenger;
import org.masd.passenger.domain.TripRequest;
import org.masd.passenger.events.BeforeDeletePassenger;
import org.masd.passenger.model.TripRequestDTO;
import org.masd.passenger.repos.PassengerRepository;
import org.masd.passenger.repos.TripRequestRepository;
import org.masd.passenger.util.NotFoundException;
import org.masd.passenger.util.ReferencedException;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TripRequestService {

    private final TripRequestRepository tripRequestRepository;
    private final PassengerRepository passengerRepository;

    public TripRequestService(final TripRequestRepository tripRequestRepository,
            final PassengerRepository passengerRepository) {
        this.tripRequestRepository = tripRequestRepository;
        this.passengerRepository = passengerRepository;
    }

    public List<TripRequestDTO> findAll() {
        final List<TripRequest> tripRequests = tripRequestRepository.findAll(Sort.by("id"));
        return tripRequests.stream()
                .map(tripRequest -> mapToDTO(tripRequest, new TripRequestDTO()))
                .toList();
    }

    public TripRequestDTO get(final Long id) {
        return tripRequestRepository.findById(id)
                .map(tripRequest -> mapToDTO(tripRequest, new TripRequestDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final TripRequestDTO tripRequestDTO) {
        final TripRequest tripRequest = new TripRequest();
        mapToEntity(tripRequestDTO, tripRequest);
        return tripRequestRepository.save(tripRequest).getId();
    }

    public void update(final Long id, final TripRequestDTO tripRequestDTO) {
        final TripRequest tripRequest = tripRequestRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(tripRequestDTO, tripRequest);
        tripRequestRepository.save(tripRequest);
    }

    public void delete(final Long id) {
        final TripRequest tripRequest = tripRequestRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        tripRequestRepository.delete(tripRequest);
    }

    private TripRequestDTO mapToDTO(final TripRequest tripRequest,
            final TripRequestDTO tripRequestDTO) {
        tripRequestDTO.setId(tripRequest.getId());
        tripRequestDTO.setPickupLocation(tripRequest.getPickupLocation());
        tripRequestDTO.setDestinationLocation(tripRequest.getDestinationLocation());
        tripRequestDTO.setStatus(tripRequest.getStatus());
        tripRequestDTO.setPrice(tripRequest.getPrice());
        tripRequestDTO.setPassenger(tripRequest.getPassenger() == null ? null : tripRequest.getPassenger().getId());
        return tripRequestDTO;
    }

    private TripRequest mapToEntity(final TripRequestDTO tripRequestDTO,
            final TripRequest tripRequest) {
        tripRequest.setPickupLocation(tripRequestDTO.getPickupLocation());
        tripRequest.setDestinationLocation(tripRequestDTO.getDestinationLocation());
        tripRequest.setStatus(tripRequestDTO.getStatus());
        tripRequest.setPrice(tripRequestDTO.getPrice());
        final Passenger passenger = tripRequestDTO.getPassenger() == null ? null : passengerRepository.findById(tripRequestDTO.getPassenger())
                .orElseThrow(() -> new NotFoundException("passenger not found"));
        tripRequest.setPassenger(passenger);
        return tripRequest;
    }

    @EventListener(BeforeDeletePassenger.class)
    public void on(final BeforeDeletePassenger event) {
        final ReferencedException referencedException = new ReferencedException();
        final TripRequest passengerTripRequest = tripRequestRepository.findFirstByPassengerId(event.getId());
        if (passengerTripRequest != null) {
            referencedException.setKey("passenger.tripRequest.passenger.referenced");
            referencedException.addParam(passengerTripRequest.getId());
            throw referencedException;
        }
    }

}
