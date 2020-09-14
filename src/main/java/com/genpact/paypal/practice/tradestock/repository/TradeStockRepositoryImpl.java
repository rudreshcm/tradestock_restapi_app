package com.genpact.paypal.practice.tradestock.repository;

import com.genpact.paypal.practice.tradestock.model.QTradeStock;
import com.genpact.paypal.practice.tradestock.model.QUser;
import com.genpact.paypal.practice.tradestock.model.TradeStock;
import com.genpact.paypal.practice.tradestock.model.User;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RequiredArgsConstructor
public class TradeStockRepositoryImpl implements TradeStockRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<TradeStock> findTradeStocksFilteredByType(String type) {

        JPAQuery query = new JPAQuery(entityManager);

        QTradeStock qTradeStock = QTradeStock.tradeStock;
        BooleanExpression predicate = qTradeStock.tradeType.name.equalsIgnoreCase(type);

        query.select(Projections.fields(TradeStock.class,
                qTradeStock.id,qTradeStock.tradeType)).from(qTradeStock).where(predicate);

        List<TradeStock> stocks = query.fetch();

        return stocks;
    }

    @Override
    public List<User> findUserWithMaxPriceOfType(String buy) {
        JPAQuery query = new JPAQuery(entityManager);
        QTradeStock qTradeStock = QTradeStock.tradeStock;
        QUser qUser = QUser.user;

        BooleanExpression onCondition = qTradeStock.user.id.eq(qUser.id);

        BooleanExpression whereCondition = qTradeStock.price
                .in(JPAExpressions.select(qTradeStock.price.max()).from(qTradeStock))
                .and(qTradeStock.tradeType.name.equalsIgnoreCase(buy));

        query.select(Projections.fields(User.class, qUser.id,qUser.firstName,qUser.lastName))
                .from(qUser).innerJoin(qTradeStock).on(onCondition)
                .where(whereCondition);

        return query.fetch();
    }
}
