package com.example.server_9dokme.common.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {

        Info info = new Info().title("9DokMe API Test").version("v0.0.1");
        info.description("9DokMe 오픈 API 입니다.");
        return new OpenAPI().info(info);
    }
}
