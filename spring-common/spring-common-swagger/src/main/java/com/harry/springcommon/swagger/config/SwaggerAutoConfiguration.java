package com.harry.springcommon.swagger.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.harry.springcommon.swagger.properties.SwaggerProperties;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.OAuth;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableAutoConfiguration
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerAutoConfiguration {

  private static final String DEFAULT_EXCLUDE_PATH = "/error";
  private static final String BASE_PATH = "/**";

  @Autowired
  private SwaggerProperties swaggerProperties;

  @Bean
  public Docket api() {
    // base-path处理
    if (CollectionUtils.isEmpty(swaggerProperties.getBasePath())) {
      swaggerProperties.getBasePath().add(BASE_PATH);
    }
    //noinspection unchecked
    List<Predicate<String>> basePath = Lists.newArrayList();
    swaggerProperties.getBasePath().forEach(path -> basePath.add(PathSelectors.ant(path)));

    // exclude-path处理
    if (CollectionUtils.isEmpty(swaggerProperties.getExcludePath())) {
      swaggerProperties.getExcludePath().add(DEFAULT_EXCLUDE_PATH);
    }
    List<Predicate<String>> excludePath = Lists.newArrayList();
    swaggerProperties.getExcludePath().forEach(path -> excludePath.add(PathSelectors.ant(path)));

    //noinspection Guava
    return new Docket(DocumentationType.SWAGGER_2)
        .host(swaggerProperties.getHost())
        .apiInfo(apiInfo(swaggerProperties)).select()
        .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))
        .paths(Predicates.and(Predicates.not(Predicates.or(excludePath)), Predicates.or(basePath)))
        .build()
        .securitySchemes(Collections.singletonList(securitySchema()))
        .securityContexts(Collections.singletonList(securityContext()))
        .pathMapping("/");
  }

  private ApiInfo apiInfo(SwaggerProperties swaggerProperties) {
    return new ApiInfoBuilder()
        .title(swaggerProperties.getTitle())
        .description(swaggerProperties.getDescription())
        .license(swaggerProperties.getLicense())
        .licenseUrl(swaggerProperties.getLicenseUrl())
        .termsOfServiceUrl(swaggerProperties.getTermsOfServiceUrl())
        .contact(new Contact(
            swaggerProperties.getContact().getName(),
            swaggerProperties.getContact().getUrl(),
            swaggerProperties.getContact().getEmail())
        )
        .version(swaggerProperties.getVersion())
        .build();
  }

  private OAuth securitySchema() {
    SwaggerProperties.Authorization authorization = swaggerProperties.getAuthorization();

    AuthorizationScope[] scopes = new AuthorizationScope[]{
        new AuthorizationScope(authorization.getScope(), authorization.getScopeDesc())
    };
    ResourceOwnerPasswordCredentialsGrant grant = new ResourceOwnerPasswordCredentialsGrant(
        authorization.getTokenUrl());

    return new OAuthBuilder()
        .name(swaggerProperties.getAuthorization().getName())
        .scopes(Arrays.asList(scopes))
        .grantTypes(Arrays.asList(grant))
        .build();
  }

  /**
   * 配置默认的全局鉴权策略的开关，通过正则表达式进行匹配；默认匹配所有URL
   *
   * @return
   */
  private SecurityContext securityContext() {
    SwaggerProperties.Authorization authorization = swaggerProperties.getAuthorization();

    AuthorizationScope[] scopes = new AuthorizationScope[]{
        new AuthorizationScope(authorization.getScope(), authorization.getScopeDesc())
    };
    SecurityReference securityReference = SecurityReference
        .builder()
        .reference(authorization.getName())
        .scopes(scopes)
        .build();
    return SecurityContext.builder()
        .securityReferences(Collections.singletonList(securityReference))
        .forPaths(PathSelectors.regex(authorization.getAuthRegex()))
        .build();
  }

  @Bean
  public SecurityConfiguration securityInfo() {
    SwaggerProperties.Authorization authorization = swaggerProperties.getAuthorization();
    return new SecurityConfiguration(
        authorization.getCliendId(),
        authorization.getClientSecret(),
        authorization.getName(),
        "REINS", "", null, false
    );
  }

}
