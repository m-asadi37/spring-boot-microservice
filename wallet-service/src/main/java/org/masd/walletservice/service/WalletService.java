package org.masd.walletservice.service;

import org.masd.walletservice.domain.Wallet;
import org.masd.walletservice.events.BeforeDeleteWallet;
import org.masd.walletservice.model.WalletDTO;
import org.masd.walletservice.repos.WalletRepository;
import org.masd.walletservice.util.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final ApplicationEventPublisher publisher;

    public WalletService(final WalletRepository walletRepository,
            final ApplicationEventPublisher publisher) {
        this.walletRepository = walletRepository;
        this.publisher = publisher;
    }

    public List<WalletDTO> findAll() {
        final List<Wallet> wallets = walletRepository.findAll(Sort.by("id"));
        return wallets.stream()
                .map(wallet -> mapToDTO(wallet, new WalletDTO()))
                .toList();
    }

    public WalletDTO get(final Long id) {
        return walletRepository.findById(id)
                .map(wallet -> mapToDTO(wallet, new WalletDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final WalletDTO walletDTO) {
        final Wallet wallet = new Wallet();
        mapToEntity(walletDTO, wallet);
        return walletRepository.save(wallet).getId();
    }

    public void update(final Long id, final WalletDTO walletDTO) {
        final Wallet wallet = walletRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(walletDTO, wallet);
        walletRepository.save(wallet);
    }

    public void delete(final Long id) {
        final Wallet wallet = walletRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        publisher.publishEvent(new BeforeDeleteWallet(id));
        walletRepository.delete(wallet);
    }

    private WalletDTO mapToDTO(final Wallet wallet, final WalletDTO walletDTO) {
        walletDTO.setId(wallet.getId());
        walletDTO.setBalance(wallet.getBalance());
        return walletDTO;
    }

    private Wallet mapToEntity(final WalletDTO walletDTO, final Wallet wallet) {
        wallet.setBalance(walletDTO.getBalance());
        return wallet;
    }

}
