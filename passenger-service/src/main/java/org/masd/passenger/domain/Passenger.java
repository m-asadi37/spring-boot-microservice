package org.masd.passenger.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "Passenger")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Passenger extends BaseData {

    public Passenger(Long id) {
        super(id);
    }

    @Column(nullable = false, unique = true)
    private String phone;

    @Column
    private String name;

    @Column
    private String family;

    private Long walletId;

    /*
    TODO: wallet id and requests
    @OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL)
    private Set<TripRequest> tripRequests = new HashSet<>();*/

}
