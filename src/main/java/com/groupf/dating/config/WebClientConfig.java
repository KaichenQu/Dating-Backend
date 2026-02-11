package com.groupf.dating.config;

import com.groupf.dating.common.ApiConstants;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfig {

    @Value("${claude.api.key}")
    private String claudeApiKey;

    @Value("${claude.api.url}")
    private String claudeApiUrl;

    @Bean
    public WebClient claudeWebClient() {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,
                        ApiConstants.CONNECTION_TIMEOUT_SECONDS * 1000)
                .responseTimeout(Duration.ofSeconds(ApiConstants.API_TIMEOUT_SECONDS))
                .doOnConnected(conn -> conn
                        .addHandlerLast(new ReadTimeoutHandler(
                                ApiConstants.API_TIMEOUT_SECONDS, TimeUnit.SECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(
                                ApiConstants.API_TIMEOUT_SECONDS, TimeUnit.SECONDS)));

        return WebClient.builder()
                .baseUrl(claudeApiUrl)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .defaultHeader(ApiConstants.HEADER_API_KEY, claudeApiKey)
                .defaultHeader(ApiConstants.HEADER_API_VERSION, ApiConstants.CLAUDE_API_VERSION)
                .defaultHeader(ApiConstants.HEADER_CONTENT_TYPE, ApiConstants.MEDIA_TYPE_JSON)
                .build();
    }
}
