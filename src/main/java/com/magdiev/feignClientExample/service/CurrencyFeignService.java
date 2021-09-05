package com.magdiev.feignClientExample.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface CurrencyFeignService {
    BigDecimal getRate(String date);
}
