package com.magdiev.feignClientExample.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "example", url = "${openexchangeratesUrl}")
public interface CurrencyFeignClient {
    @GetMapping("{date}.json?app_id={appId}&base={baseCurrency}")
    String getRate(@PathVariable String date, @PathVariable String appId, @PathVariable String baseCurrency);
}
