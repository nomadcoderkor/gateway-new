package com.samsung.cloud.hcms.apigatewayservice.filter;

import com.samsung.cloud.hcms.apigatewayservice.filter.HcmsApiFilter.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class OpenApiFilter extends AbstractGatewayFilterFactory<OpenApiFilter.Config> {
  public OpenApiFilter() {
    super(Config.class);
  }

  @Override
  public GatewayFilter apply(OpenApiFilter.Config config) {
    // Pre Filter
    return (exchange, chain) ->  {
      ServerHttpRequest request = exchange.getRequest();
      ServerHttpResponse response = exchange.getResponse();
      log.info("Pre HCMS API : request id :: {}", request.getId());

      // POST Filter
      return chain.filter(exchange).then(Mono.fromRunnable(() -> {
        log.info("After HCMS API : Response code :: {}", response.getStatusCode());
      }));
    };
  }

  public static class Config {
    // 필요한 컨피그 정보가 있다면 이곳에서 넣어준다.
  }
}
