package com.gemepro.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@ConditionalOnProperty(prefix = "spring.profiles.", name = "active", havingValue = "test")
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createAppApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("app")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .apiInfo(appApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gemepro.api.controller"))
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo appApiInfo() {
        return new ApiInfoBuilder()
                .title("App Api文档")
                .description("威廉古堡：http://www.baidu.com")
                .termsOfServiceUrl("http://www.baidu.com")
                .contact("威廉古堡")
                .version("1.0")
                .build();
    }


}