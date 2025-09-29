package org.masd.passenger.mapper;

import org.masd.passenger.domain.Passenger;
import org.masd.passenger.domain.Wallet;
import org.masd.passenger.model.PassengerDTO;

public class PassengerMapper {

    public static PassengerDTO toDto(Passenger entity) {
        PassengerDTO dto = PassengerDTO.builder()
                .phone(entity.getPhone())
                .name(entity.getName())
                .family(entity.getFamily())
                .walletId(
                        entity.getWallet() == null ? null
                                : entity.getWallet().getId())
                .build();
        dto.setId(entity.getId());
        return dto;
    }


    public static Passenger toEntity(PassengerDTO dto) {
        Passenger entity = Passenger.builder()
                .phone(dto.getPhone())
                .name(dto.getName())
                .family(dto.getFamily())
                .wallet(
                        dto.getWalletId() == null ? new Wallet()
                                : new Wallet(dto.getId()))
                .build();
        entity.setId(dto.getId());
        return entity;
    }
}
