package com.anz.ws.accounts.config;

import io.swagger.annotations.SwaggerDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@SwaggerDefinition(produces = "application/json", schemes = {SwaggerDefinition.Scheme.HTTP})
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.anz.ws.accounts.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("Accounts API", "Accounts Api to get the accounts and transaction list.",
                "1.0", "Terms of service", new Contact("Vishal", "www.anz.com", "vishal.vattam@anz.com"),
                "ANZ Wholesale Accounts API 1.0", "API license URL", ApiInfo.DEFAULT.getVendorExtensions());
    }
}