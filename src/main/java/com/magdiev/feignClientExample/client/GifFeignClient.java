package com.magdiev.feignClientExample.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "gif", url = "${giphyUrl}")
public interface GifFeignClient {
    @GetMapping("?api_key={apiKey}&tag={tag}&rating=g")
    String getGif(@PathVariable String apiKey, @PathVariable String tag);
}
