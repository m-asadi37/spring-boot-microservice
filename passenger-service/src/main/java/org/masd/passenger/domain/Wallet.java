package org.masd.passenger.domain;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "Wallet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wallet extends BaseData {

    public Wallet(Long id) {
        super(id);
    }

    @Column(nullable = false)
    private Double balance = 0.0;

    @OneToOne(mappedBy = "wallet", fetch = FetchType.LAZY)
    private Passenger passenger;
}
