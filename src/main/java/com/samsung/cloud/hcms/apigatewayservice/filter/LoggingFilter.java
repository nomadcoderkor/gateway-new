package com.samsung.cloud.hcms.apigatewayservice.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class LoggingFilter extends AbstractGatewayFilterFactory<LoggingFilter.Config> {
  public LoggingFilter(){
    super(Config.class);
  }

  @Override
  public GatewayFilter apply(Config config) {
    // Pre Filter
    return (exchange, chain) ->  {
      ServerHttpRequest request = exchange.getRequest();
      ServerHttpResponse response = exchange.getResponse();
      log.info("Logging Pre Filter: request id :: {}", request.getId());

      // POST Filter
      return chain.filter(exchange).then(Mono.fromRunnable(() -> {
        log.info("Loogging Post Filter: Response code :: {}", response.getStatusCode());
      }));
    };
  }

  @Data
  public static class Config{
    private String loggingMessage;
    private boolean preLogger;
    private boolean postLogger;
  }
}
