package com.github.novicezk.midjourney.controller;

import io.swagger.annotations.Api;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

@Api(tags = "获取图片信息")
@RestController
@RequestMapping("/discord-images")
public class ImageController {
    static String cdn = "https://cdn.discordapp.com/";

    @Resource
    private CloseableHttpClient httpClient;

    @GetMapping
    public ResponseEntity<byte[]> getImage(@RequestParam(name="url") String url) throws IOException {
        // 替换为你的图片路径

//        String imageUrl = cdn + imagePath;


        byte[] imageBytes = downloadImage(url);

        if (imageBytes != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public byte[] downloadImage(String imageUrl) throws IOException {
        HttpGet request = new HttpGet(imageUrl);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toByteArray(entity);
            }
        }
        return null;
    }
}
