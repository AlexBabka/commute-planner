package com.github.alexbabka.planner.config;

import com.github.alexbabka.planner.client.log.LoggingRequestInterceptor;
import com.github.alexbabka.planner.config.properties.HttpConnectionProperties;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {
    @Autowired
    private HttpConnectionProperties httpConnectionProperties;

    @Bean
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager result = new PoolingHttpClientConnectionManager();
        result.setMaxTotal(httpConnectionProperties.getMaxTotal());
        result.setDefaultMaxPerRoute(httpConnectionProperties.getMaxPerRoute());

        return result;
    }

    @Bean
    public RequestConfig requestConfig() {
        return RequestConfig.custom()
                .setConnectionRequestTimeout(httpConnectionProperties.getRequestTimeout())
                .setConnectTimeout(httpConnectionProperties.getConnectTimeout())
                .setSocketTimeout(httpConnectionProperties.getSocketTimeout())
                .build();
    }

    @Bean
    public CloseableHttpClient httpClient() {
        return HttpClientBuilder
                .create()
                .setConnectionManager(poolingHttpClientConnectionManager())
                .setDefaultRequestConfig(requestConfig())
                .build();
    }

    @Bean
    public RestTemplate createRestTemplate(final RestTemplateBuilder restTemplateBuilder) {
        // Create request factory with connection pooling
        ClientHttpRequestFactory requestFactory
                = new BufferingClientHttpRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient()));

        return restTemplateBuilder
                .requestFactory(requestFactory)
                .additionalInterceptors(new LoggingRequestInterceptor())
                .build();
    }
}
