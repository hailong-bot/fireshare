package com.hailong.fireshare.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class OpenApiConfig {
    /*@Bean
    public OpenAPI hOpenApi() {
        return new OpenAPI()
                .info(new Info().title("API")
                        .description("file share")
                        .version("v1.0.0")
                        .license(new License().name("MIT").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("github")
                        .url("https://github.com/hailong-bot/fireshare.git"));
    }*/

    @Bean(value = "indexApi")
    public Docket indexApi() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("网站前端接口分组").apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hailong.fireshare.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API")
                .description("file share")
                .version("1.0")
                .build();
    }
}
