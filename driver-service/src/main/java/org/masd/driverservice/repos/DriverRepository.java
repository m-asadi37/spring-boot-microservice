package org.masd.driverservice.repos;

import org.masd.driverservice.domain.Driver;
import org.masd.driverservice.domain.DriverStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findByPhone(String phone);
    List<Driver> findByStatus(DriverStatus status);
}
