package org.masd.walletservice.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.masd.walletservice.model.WalletDTO;
import org.masd.walletservice.service.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/wallet", produces = MediaType.APPLICATION_JSON_VALUE)
public class WalletApi {

    private final WalletService walletService;

    public WalletApi(final WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping
    public ResponseEntity<List<WalletDTO>> getAllWallets() {
        return ResponseEntity.ok(walletService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WalletDTO> getWallet(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(walletService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createWallet(@RequestBody @Valid final WalletDTO walletDTO) {
        final Long createdId = walletService.create(walletDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateWallet(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final WalletDTO walletDTO) {
        walletService.update(id, walletDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteWallet(@PathVariable(name = "id") final Long id) {
        walletService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
