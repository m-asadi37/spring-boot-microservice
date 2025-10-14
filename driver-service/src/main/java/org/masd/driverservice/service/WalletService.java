package org.masd.driverservice.service;

import org.masd.driverservice.dto.WalletDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("wallet-service")
public interface WalletService {

    @PostMapping("api/wallet")
    ResponseEntity<Long> createWallet(WalletDTO walletDTO);
}
