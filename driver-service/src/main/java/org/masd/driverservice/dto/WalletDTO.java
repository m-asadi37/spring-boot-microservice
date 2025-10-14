package org.masd.driverservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class WalletDTO {

    private Long id;

    @NotNull
    private Double balance;

}
