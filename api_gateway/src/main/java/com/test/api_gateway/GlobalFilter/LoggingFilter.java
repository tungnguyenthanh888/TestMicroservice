package com.test.api_gateway.GlobalFilter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@Component
public class LoggingFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String method = exchange.getRequest()
                .getMethod()
                .name();

        String uri = exchange.getRequest()
                .getURI()
                .toString();

        Date requestTime = new Date();

        log.info(
                "[REQUEST] Time={} Method={} URI={}",
                requestTime,
                method,
                uri
        );

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
