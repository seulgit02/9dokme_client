package com.example.server_9dokme.common.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {

        Info info = new Info()
                .title("9DokMe API Test")
                .version("v0.0.1")
                .description("9DokMe 오픈 API 입니다.");

        // 서버 URL을 명시적으로 HTTPS로 설정
        Server server = new Server()
                .url("https://www.9dokme.p-e.kr")
                .description("배포된 서버");

        return new OpenAPI()
                .info(info)
                .servers(List.of(server)); // 서버 리스트에 추가
    }
}

