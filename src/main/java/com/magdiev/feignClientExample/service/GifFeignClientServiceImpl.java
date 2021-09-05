package com.magdiev.feignClientExample.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.magdiev.feignClientExample.client.GifFeignClient;
import com.magdiev.feignClientExample.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@PropertySource(value = "classpath:application.properties")
public class GifFeignClientServiceImpl  implements GifFeignClientService{
    private Environment environment;
    private GifFeignClient gifFeignClient;
    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Autowired
    public void setGifFeignClient(GifFeignClient gifFeignClient) {
        this.gifFeignClient = gifFeignClient;
    }

    @Override
    public String getGifUrl(String tag) {
        String gifUrl = "";
        String apiKey = environment.getProperty("gifApiKey");
        try {
            JsonNode response = new ObjectMapper().readTree(gifFeignClient.getGif(apiKey, tag));
            if (response == null) {
                throw new NotFoundException();
            }
            gifUrl = response.get("data").get("images").get("original").get("url").asText();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "<img src=\"" + gifUrl + "\">";
    }
}
