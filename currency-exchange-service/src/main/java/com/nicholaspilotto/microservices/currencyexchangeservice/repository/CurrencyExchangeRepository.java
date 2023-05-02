package com.nicholaspilotto.microservices.currencyexchangeservice.repository;

import com.nicholaspilotto.microservices.currencyexchangeservice.dao.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
  CurrencyExchange findByFromAndTo(String from, String to);
}
