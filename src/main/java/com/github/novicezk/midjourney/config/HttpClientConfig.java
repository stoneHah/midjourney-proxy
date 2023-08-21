package com.github.novicezk.midjourney.config;

import com.github.novicezk.midjourney.ProxyProperties;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class HttpClientConfig {
    @Resource
    private ProxyProperties properties;
    @Bean
    public CloseableHttpClient httpClientProxy() {
        ProxyProperties.ProxyConfig proxy = properties.getProxy();

        HttpHost proxyHost = new HttpHost(proxy.getHost(), proxy.getPort());
        RequestConfig config = RequestConfig.custom()
                .setProxy(proxyHost)
                .build();
        return HttpClientBuilder.create()
                .setDefaultRequestConfig(config)
                .build();
    }
}
