package com.genpact.paypal.practice.tradestock.model;

import javax.persistence.*;

@Entity
@Table(name = "trade_stock")
public class TradeStock extends Storable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trade_stock_type_id", referencedColumnName = "id")
    private TradeType tradeType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trade_stock_user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trade_stock_company_id", referencedColumnName = "id")
    private Company company;

    private Integer shares;

    private Double price;


    public TradeStock(){

    }

    public TradeStock(TradeType tradeType, User user, Company company, Integer shares, Double price) {
        this.tradeType = tradeType;
        this.user = user;
        this.company = company;
        this.shares = shares;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TradeType getTradeType() {
        return tradeType;
    }

    public void setTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Integer getShares() {
        return shares;
    }

    public void setShares(Integer shares) {
        this.shares = shares;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
