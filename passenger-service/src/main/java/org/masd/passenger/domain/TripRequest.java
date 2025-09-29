package org.masd.passenger.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "trip_request")
@Getter
@Setter
public class TripRequest extends BaseData {

    @Column(nullable = false)
    private String pickupLocation;

    @Column(nullable = false)
    private String destinationLocation;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TripRequestStatus status;

    @Column(nullable = false)
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

}
