package com.magdiev.feignClientExample.controller;

import com.magdiev.feignClientExample.service.CurrencyFeignService;
import com.magdiev.feignClientExample.service.GifFeignClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
public class MainController {
    private CurrencyFeignService currencyFeignService;
    private GifFeignClientService gifFeignClientService;

    @GetMapping
    public String index() throws IOException {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        String tag = "";

        BigDecimal rateToday = currencyFeignService.getRate(today.toString());
        BigDecimal rateYesterday = currencyFeignService.getRate(yesterday.toString());

        int res = rateToday.compareTo(rateYesterday);
        if (res >= 0) {
            tag = "rich";
        } else {
            tag = "broke";
        }
        return gifFeignClientService.getGifUrl(tag);
    }

    @Autowired
    public void setCurrencyFeignService(CurrencyFeignService currencyFeignService) {
        this.currencyFeignService = currencyFeignService;
    }

    @Autowired
    public void setGifFeignClientService(GifFeignClientService gifFeignClientService) {
        this.gifFeignClientService = gifFeignClientService;
    }

}
