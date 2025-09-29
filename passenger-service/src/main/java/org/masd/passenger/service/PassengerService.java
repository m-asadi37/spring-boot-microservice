package org.masd.passenger.service;

import org.masd.passenger.domain.Passenger;
import org.masd.passenger.events.BeforeDeletePassenger;
import org.masd.passenger.events.BeforeDeleteWallet;
import org.masd.passenger.mapper.PassengerMapper;
import org.masd.passenger.model.PassengerDTO;
import org.masd.passenger.repos.PassengerRepository;
import org.masd.passenger.repos.WalletRepository;
import org.masd.passenger.util.NotFoundException;
import org.masd.passenger.util.ReferencedException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PassengerService {

    private final PassengerRepository passengerRepository;
    private final WalletRepository walletRepository;
    private final ApplicationEventPublisher publisher;

    public PassengerService(final PassengerRepository passengerRepository,
            final WalletRepository walletRepository, final ApplicationEventPublisher publisher) {
        this.passengerRepository = passengerRepository;
        this.walletRepository = walletRepository;
        this.publisher = publisher;
    }

    public List<PassengerDTO> findAll() {
        final List<Passenger> passengers = passengerRepository.findAll(Sort.by("id"));
        return passengers.stream()
                .map(PassengerMapper::toDto)
                .toList();
    }

    public PassengerDTO get(final Long id) {
        return passengerRepository.findById(id)
                .map(PassengerMapper::toDto)
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final PassengerDTO passengerDTO) {
        final Passenger passenger = PassengerMapper.toEntity(passengerDTO);
        return passengerRepository.save(passenger).getId();
    }

    public void delete(final Long id) {
        final Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        publisher.publishEvent(new BeforeDeletePassenger(id));
        passengerRepository.delete(passenger);
    }

    public boolean phoneExists(final String phone) {
        return passengerRepository.existsByPhoneIgnoreCase(phone);
    }

    public boolean walletExists(final Long id) {
        return passengerRepository.existsByWalletId(id);
    }

    @EventListener(BeforeDeleteWallet.class)
    public void on(final BeforeDeleteWallet event) {
        final ReferencedException referencedException = new ReferencedException();
        final Passenger walletPassenger = passengerRepository.findFirstByWalletId(event.getId());
        if (walletPassenger != null) {
            referencedException.setKey("wallet.passenger.wallet.referenced");
            referencedException.addParam(walletPassenger.getId());
            throw referencedException;
        }
    }

}
