package org.masd.tripservice.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.masd.tripservice.domain.TripRequestStatus;


@Getter
@Setter
public class TripRequestDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String pickupLocation;

    @NotNull
    @Size(max = 255)
    private String destinationLocation;

    @NotNull
    private TripRequestStatus status;

    @NotNull
    private Double price;

    private Long passengerId;

}
