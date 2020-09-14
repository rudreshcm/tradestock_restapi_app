package com.genpact.paypal.practice.tradestock.repository;

import com.genpact.paypal.practice.tradestock.model.TradeStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface TradeStockRepository extends JpaRepository<TradeStock, Long> ,
        QuerydslPredicateExecutor<TradeStock> , TradeStockRepositoryCustom {

}
