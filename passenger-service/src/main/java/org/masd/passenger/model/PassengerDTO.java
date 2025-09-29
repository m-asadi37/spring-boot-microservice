package org.masd.passenger.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PassengerDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String phone;

    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String family;

    private Long walletId;

}
