package org.masd.driverservice.model;


import org.masd.driverservice.domain.DriverStatus;

public record DriverDto(
        Long id,
        String phone,
        String name,
        String family,
        String licenseNumber,
        DriverStatus status,
        Long walletId
) {}

