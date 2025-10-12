package org.masd.driverservice.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "driver")
public class Driver extends BaseData {

    public Driver(Long id) { super(id); }

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String family;

    @Column(nullable = false)
    private String licenseNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DriverStatus status = DriverStatus.OFFLINE;

    private Long walletId;

//    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<TripRequest> trips = new HashSet<>();

}
