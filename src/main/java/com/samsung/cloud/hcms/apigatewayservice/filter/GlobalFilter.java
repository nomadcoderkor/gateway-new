package com.samsung.cloud.hcms.apigatewayservice.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {
  public GlobalFilter() {
    super(Config.class);
  }

  @Override
  public GatewayFilter apply(Config config) {
    // Pre Filter
    return (exchange, chain) ->  {
      ServerHttpRequest request = exchange.getRequest();
      ServerHttpResponse response = exchange.getResponse();
      log.info("Global Filter ::::");
      log.info("public API::: {}", request.getURI());
      // POST Filter
      return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//        log.info("API Post Filter: Response code :: {}", response.getStatusCode());
      }));
    };
  }

  public static class Config {

  }
}
