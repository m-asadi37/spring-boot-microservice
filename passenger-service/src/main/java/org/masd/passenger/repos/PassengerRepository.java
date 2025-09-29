package org.masd.passenger.repos;

import org.masd.passenger.domain.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    Passenger findFirstByWalletId(Long id);

    boolean existsByPhoneIgnoreCase(String phone);

    boolean existsByWalletId(Long id);

}
