package com.samsung.cloud.hcms.apigatewayservice.filter;

import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class HcmsApiFilter extends AbstractGatewayFilterFactory<HcmsApiFilter.Config> {
  public HcmsApiFilter() {
    super(Config.class);
  }

  @Override
  public GatewayFilter apply(Config config) {
    // Pre Filter
    return (exchange, chain) ->  {
      ServerHttpRequest request = exchange.getRequest();
      ServerHttpResponse response = exchange.getResponse();
      log.info("Pre HCMS API : request id :: {}", request.getId());
      tokenValidation("DFASDFASDFASDFDSFASDFASDFASDFASDF");
      if(true){
        exchange.getRequest().mutate().path("/public/login").build();
        ServerWebExchange modifiedExchange = exchange.mutate()
            .request(exchange.getRequest().mutate().path("/public/login").build())
            .build();
        return chain.filter(modifiedExchange);
      }
      // POST Filter
      return chain.filter(exchange).then(Mono.fromRunnable(() -> {
        log.info("After HCMS API : Response code :: {}", response.getStatusCode());
      }));
    };
  }

  private void tokenValidation( String token) {
    log.info("### Token Validation ###");
    log.info(token);
    log.info("########################");
  }

  public static class Config {
    // 필요한 컨피그 정보가 있다면 이곳에서 넣어준다.
  }
}
