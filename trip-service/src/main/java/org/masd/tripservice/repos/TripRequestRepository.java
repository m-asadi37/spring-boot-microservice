package org.masd.tripservice.repos;

import org.masd.tripservice.domain.TripRequest;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TripRequestRepository extends JpaRepository<TripRequest, Long> {

    TripRequest findFirstByPassengerId(Long id);

}
