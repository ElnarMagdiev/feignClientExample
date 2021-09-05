package com.magdiev.feignClientExample;

import com.magdiev.feignClientExample.controller.MainController;
import com.magdiev.feignClientExample.service.CurrencyFeignService;
import com.magdiev.feignClientExample.service.GifFeignClientService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
public class ControllerTest {
    @MockBean
    CurrencyFeignService currencyFeignService;
    @MockBean
    GifFeignClientService gifFeignClientService;

    @Autowired
    MainController mainController;

    @Test
    public void simpleTest() throws IOException {
        BigDecimal currencyRateToday = new BigDecimal("74.02");
        BigDecimal currencyRateYesterday = new BigDecimal("73.01");

        String gifUrl = "<img src=\"https://giphy.com/clips/thefastsaga-fast-and-furious-tokyo-drift-" +
                "KsKQL76tx5tzT9a9G9\">";
        String tag = "rich";

        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);

        Mockito.when(currencyFeignService.getRate(today.toString())).thenReturn(currencyRateToday);
        Mockito.when(currencyFeignService.getRate(yesterday.toString())).thenReturn(currencyRateYesterday);

        Mockito.when(gifFeignClientService.getGifUrl(tag)).thenReturn(gifUrl);

        Assertions.assertTrue(mainController.index().contains("<img src=\"https://giphy.com/clips/thefastsaga-fast-" +
                "and-furious-tokyo-drift-KsKQL76tx5tzT9a9G9\">"));
    }
}
