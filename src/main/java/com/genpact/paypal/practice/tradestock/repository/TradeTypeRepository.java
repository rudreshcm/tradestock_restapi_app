package com.genpact.paypal.practice.tradestock.repository;

import com.genpact.paypal.practice.tradestock.model.TradeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeTypeRepository extends JpaRepository<TradeType, Long> {
}
