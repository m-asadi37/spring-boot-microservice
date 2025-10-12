package org.masd.tripservice.service;

import org.masd.tripservice.domain.TripRequest;
import org.masd.tripservice.model.TripRequestDTO;
import org.masd.tripservice.repos.TripRequestRepository;
import org.masd.tripservice.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TripRequestService {

    private final TripRequestRepository tripRequestRepository;

    public TripRequestService(final TripRequestRepository tripRequestRepository) {
        this.tripRequestRepository = tripRequestRepository;
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
        tripRequestDTO.setPassengerId(tripRequest.getPassengerId());
        return tripRequestDTO;
    }

    private TripRequest mapToEntity(final TripRequestDTO tripRequestDTO,
            final TripRequest tripRequest) {
        tripRequest.setPickupLocation(tripRequestDTO.getPickupLocation());
        tripRequest.setDestinationLocation(tripRequestDTO.getDestinationLocation());
        tripRequest.setStatus(tripRequestDTO.getStatus());
        tripRequest.setPrice(tripRequestDTO.getPrice());
        tripRequest.setPassengerId(tripRequestDTO.getPassengerId());
        return tripRequest;
    }

}
