package com.github.alexbabka.planner.client.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Interceptor to log requests sent by RestTemplate.
 */
@Component
public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {
    private Logger logger = LoggerFactory.getLogger(LoggingRequestInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        if (logger.isDebugEnabled()) {
            traceRequest(request, body);
        }

        ClientHttpResponse response = execution.execute(request, body);

        if (logger.isDebugEnabled()) {
            traceResponse(response);
        }

        return response;
    }

    private void traceRequest(HttpRequest request, byte[] body) throws IOException {
        logger.debug("===========================request begin================================================");
        logger.debug("URI         : " + request.getURI());
        logger.debug("Method      : " + request.getMethod());
        logger.debug("Headers     : " + request.getHeaders());
        logger.debug("Request body: " + new String(body, StandardCharsets.UTF_8));
        logger.debug("==========================request end================================================");
    }

    private void traceResponse(ClientHttpResponse response) throws IOException {
        InputStreamReader in = null;
        try {
            in = new InputStreamReader(response.getBody(), StandardCharsets.UTF_8);
            final BufferedReader bufferedReader = new BufferedReader(in);
            String line = bufferedReader.readLine();

            final StringBuilder inputStringBuilder = new StringBuilder();
            while (line != null) {
                inputStringBuilder.append(line);
                inputStringBuilder.append('\n');
                line = bufferedReader.readLine();
            }

            logger.debug("============================response begin==========================================");
            logger.debug("Status code  : " + Integer.toString(response.getRawStatusCode()));
            logger.debug("Status text  : " + response.getStatusText());
            logger.debug("Headers      : " + response.getHeaders());
            logger.debug("Response body: " + inputStringBuilder.toString());
            logger.debug("=======================response end=================================================");
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }
}
