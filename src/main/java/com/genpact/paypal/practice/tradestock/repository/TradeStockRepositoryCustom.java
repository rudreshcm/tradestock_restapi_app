package com.genpact.paypal.practice.tradestock.repository;

import com.genpact.paypal.practice.tradestock.model.TradeStock;
import com.genpact.paypal.practice.tradestock.model.User;

import java.util.List;

public interface TradeStockRepositoryCustom {
    List<TradeStock> findTradeStocksFilteredByType(String type);
    List<User> findUserWithMaxPriceOfType(String buy);
}
