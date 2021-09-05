package com.magdiev.feignClientExample.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.magdiev.feignClientExample.client.CurrencyFeignClient;
import com.magdiev.feignClientExample.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;

@Service
@PropertySource(value = "classpath:application.properties")
public class CurrencyFeignServiceImpl implements CurrencyFeignService {
    private CurrencyFeignClient feignClient;
    private Environment environment;

    @Autowired
    public void setFeignClient(CurrencyFeignClient feignClient) {
        this.feignClient = feignClient;
    }

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public BigDecimal getRate(String date) {
        BigDecimal rate = null;
        try {
            String appId = environment.getProperty("currencyAppId");
            String base = environment.getProperty("baseCurrency");
            String currency = environment.getProperty("currency");
            JsonNode response = new ObjectMapper().readTree(feignClient.getRate(date, appId, base));
            JsonNode rateNode = response.get("rates").get(currency);
            if (rateNode == null) {
                throw new NotFoundException();
            }
            rate = rateNode.decimalValue();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rate;
    }
}
