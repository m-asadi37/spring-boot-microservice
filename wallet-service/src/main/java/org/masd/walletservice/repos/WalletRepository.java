package org.masd.walletservice.repos;

import org.masd.walletservice.domain.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
