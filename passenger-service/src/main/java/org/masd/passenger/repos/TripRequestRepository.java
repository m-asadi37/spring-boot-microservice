package org.masd.passenger.repos;

import org.masd.passenger.domain.TripRequest;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TripRequestRepository extends JpaRepository<TripRequest, Long> {

    TripRequest findFirstByPassengerId(Long id);

}
