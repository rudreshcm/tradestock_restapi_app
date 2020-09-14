package com.genpact.paypal.practice.tradestock.repository;

import com.genpact.paypal.practice.tradestock.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findOneById(Long userId);

    Iterable<User> findAllByCountry(String usa);

    boolean existsByEmail(String email);
}
