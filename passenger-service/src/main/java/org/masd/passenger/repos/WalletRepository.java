package org.masd.passenger.repos;

import org.masd.passenger.domain.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
