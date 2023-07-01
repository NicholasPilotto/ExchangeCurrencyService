package com.nicholaspilotto.microservices.currencyexchangeservice.controller;

import com.nicholaspilotto.microservices.currencyexchangeservice.dao.CurrencyExchange;
import com.nicholaspilotto.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {
  private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
  @Autowired
  private CurrencyExchangeRepository repository;
  @Autowired
  private Environment environment;

  @GetMapping("/currency-exchange/from/{from}/to/{to}")
  public CurrencyExchange retreiveExchangeValue(@PathVariable String from, @PathVariable String to) {
    logger.info("retreiveExchangeValue called with {} to {}", from, to);

    CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);

    if (currencyExchange == null) {
      throw new RuntimeException("Unable to find data from " + from + " to " + to);
    }

    String port = environment.getProperty("local.server.port");
    currencyExchange.setEnvironment(port);

    return currencyExchange;
  }
}
