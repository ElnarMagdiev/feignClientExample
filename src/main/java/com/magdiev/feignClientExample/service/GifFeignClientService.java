package com.magdiev.feignClientExample.service;

import org.springframework.stereotype.Service;

@Service
public interface GifFeignClientService {
    String getGifUrl(String tag);
}
