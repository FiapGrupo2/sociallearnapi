package com.fiap.sociallearn.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).select()
        .apis(RequestHandlerSelectors.basePackage("com.fiap.sociallearn"))
        .paths(PathSelectors.any())
        .build()
        .securitySchemes(Collections.singletonList(apiKey()))
        .securityContexts(securityContext())
        .apiInfo(metaInfo());
  }

  private ApiKey apiKey() {
    return new ApiKey("apiKey", "Authorization", "header");
  }

  private List<SecurityContext> securityContext() {
      return List.of(SecurityContext.builder().securityReferences(defaultAuth())
              .forPaths(PathSelectors.any()).build());
  }

  private List<SecurityReference> defaultAuth() {
      AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
      AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
      authorizationScopes[0] = authorizationScope;
      
      return Arrays.asList(new SecurityReference("apiKey",authorizationScopes));
  }

  private ApiInfo metaInfo() {
    return new ApiInfoBuilder().title("Social Learn API")
        .description("Social Learn")
        .version("1.0.0")
        .build();
  }
}
