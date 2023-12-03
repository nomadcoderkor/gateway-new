package com.samsung.cloud.hcms.apigatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FilterConfig {

//  @Bean
  public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
     return builder.routes()
         .route(r -> r.path("/public/**")
             .filters(f -> f.addRequestHeader("public-request", "public-request-header")
                 .addResponseHeader("public-response", "public-response-header")
//                 .rewritePath("/integration/(?<segment>.*)","/a-integration/${segment}")
                 )
                 .uri("http://localhost:5000"))

         .route(r -> r.path("/hcms/**")
             .filters(f -> f.addRequestHeader("hcms-request", "hcms-request-header")
                 .addResponseHeader("hcms-response", "hcms-response-header"))
//                 .rewritePath("/hcms/(?<segment>.*)","/${segment}" ))
             .uri("http://localhost:5000"))
         .route(r -> r.path("/open-api/**")
             .filters(f -> f.addRequestHeader("open-api-request", "open-api-request-header")
                 .addResponseHeader("open-api-response", "open-api-response-header")
                 .rewritePath("/open-api/(?<segment>.*)","/${segment}" ))
             .uri("http://localhost:5000"))
         .build();
  }
}
