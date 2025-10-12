package org.masd.driverservice.service;

import jakarta.persistence.EntityNotFoundException;
import org.masd.driverservice.domain.Driver;
import org.masd.driverservice.domain.DriverStatus;
import org.masd.driverservice.model.DriverDto;
import org.masd.driverservice.repos.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DriverService {

    private final DriverRepository driverRepository;
    //TODO : ned for calling from wallet service
//    private final WalletRepository walletRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository/*, WalletRepository walletRepository*/) {
        this.driverRepository = driverRepository;
//        this.walletRepository = walletRepository;
    }

    @Transactional
    public DriverDto createDriver(DriverDto request) {
        driverRepository.findByPhone(request.phone()).ifPresent(d -> {
            throw new IllegalArgumentException("Driver with this phone already exists");
        });

        //TODO: create wallet
//        Wallet wallet = new Wallet();
//        wallet.setBalance(0.0);
//        walletRepository.save(wallet);

        Driver driver = new Driver();
        driver.setPhone(request.phone());
        driver.setName(request.name());
        driver.setFamily(request.family());
        driver.setLicenseNumber(request.licenseNumber());
        driver.setStatus(DriverStatus.OFFLINE);
        //TODO: set wallet id
//        driver.setWalletId(wallet);

        driverRepository.save(driver);

        return mapToDto(driver);
    }

    @Transactional(readOnly = true)
    public DriverDto getDriver(Long id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Driver not found"));
        return mapToDto(driver);
    }

    @Transactional(readOnly = true)
    public List<DriverDto> getAvailableDrivers() {
        return driverRepository.findByStatus(DriverStatus.AVAILABLE)
                .stream().map(this::mapToDto).toList();
    }

    @Transactional
    public DriverDto updateStatus(Long id, String status) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Driver not found"));
        driver.setStatus(DriverStatus.valueOf(status));
        driverRepository.save(driver);
        return mapToDto(driver);
    }

    private DriverDto mapToDto(Driver d) {
        return new DriverDto(d.getId(), d.getPhone(), d.getName(), d.getFamily(),
                d.getLicenseNumber(), d.getStatus(), d.getWalletId());
    }
}
