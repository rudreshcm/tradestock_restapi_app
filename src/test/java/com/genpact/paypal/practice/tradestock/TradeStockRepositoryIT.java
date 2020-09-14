package com.genpact.paypal.practice.tradestock;

import com.genpact.paypal.practice.tradestock.repository.CompanyRepository;
import com.genpact.paypal.practice.tradestock.repository.TradeStockRepository;
import com.genpact.paypal.practice.tradestock.repository.TradeTypeRepository;
import com.genpact.paypal.practice.tradestock.repository.UserRepository;
import com.genpact.paypal.practice.tradestock.model.Company;
import com.genpact.paypal.practice.tradestock.model.TradeStock;
import com.genpact.paypal.practice.tradestock.model.TradeType;
import com.genpact.paypal.practice.tradestock.model.User;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TradestockApplication.class)
@AutoConfigureMockMvc
public class TradeStockRepositoryIT {

    //models
    private TradeStock tradeStock;
    private TradeType sellTradeType;
    private TradeType buyTradeType;
    private Company company;
    private User user;
    private User user2;
    private User user3;

    private DateTime createdTime;
    private DateTime updatedTime;

    @Autowired
    private TradeStockRepository  tradeStockRepository;
    @Autowired
    private TradeTypeRepository tradeTypeRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {

        createdTime = new DateTime();
        updatedTime = new DateTime();

        user = new User("Kavana", "CM", "kavanacm@gmail.com", "password", "India", createdTime,
                updatedTime);
        user2 = new User("Scott", "CM", "kavanacm@gmail.com", "password", "Australia", createdTime,
                updatedTime);
        user3 = new User("Tiger", "CM", "kavanacm@gmail.com", "password", "Caneda", createdTime,
                updatedTime);
        company = new Company("ABC", "ABC Symbol");

        sellTradeType = new TradeType("sell");
        buyTradeType = new TradeType("buy");

        userRepository.save(user);
        userRepository.save(user2);
        userRepository.save(user3);

        tradeTypeRepository.save(sellTradeType);
        tradeTypeRepository.save(buyTradeType);
        companyRepository.save(company);
    }

    @After
    public void tearDown() {

        tradeStockRepository.deleteAll();
        userRepository.deleteAll();
        tradeTypeRepository.deleteAll();
        companyRepository.deleteAll();

    }


    @Test
    public void should_store_a_tradestock() {
        tradeStock = new TradeStock(sellTradeType, user, company, 14, 20000.45);
        tradeStockRepository.save(tradeStock);
    }

    @Test
    public void filter_trade_stock_by_trade_type() {
        List<TradeStock> stocks = new ArrayList<>();
        stocks.add(new TradeStock(sellTradeType, user, company, 14, 20000.45));
        stocks.add(new TradeStock(buyTradeType, user, company, 14, 20000.45));
        tradeStockRepository.saveAll(stocks);

        List<TradeStock> filteredStocks = tradeStockRepository.findTradeStocksFilteredByType("Buy");
        assertThat(filteredStocks).hasSize(1);
    }
    @Test
    public  void filter_users_with_max_share(){

        List<TradeStock> stocks = new ArrayList<>();
        stocks.add(new TradeStock(sellTradeType, user, company, 14, 50000.00));
        stocks.add(new TradeStock(buyTradeType, user2, company, 14, 10000.00));
        stocks.add(new TradeStock(buyTradeType, user3, company, 14, 20000.00));
        tradeStockRepository.saveAll(stocks);

        List<User> fiUserList = tradeStockRepository.findUserWithMaxPriceOfType("Buy");
        assertThat(fiUserList).hasSize(1);

    }
}
