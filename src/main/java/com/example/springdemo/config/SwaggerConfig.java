package com.example.springdemo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Server devServer = new Server();
        devServer.setUrl("/");

        //운영서버에 따로 띄우기 위해 서버를 추가 가능
        Server prodServer = new Server();
        prodServer.setUrl("운영 URL");

        Info info = new Info()
                .title("Swagger API") // API 문서 제목
                .version("v1.0.0") // API 문서 버전
                .description("스웨거 API"); // API 문서 설명

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer, prodServer));
    }
}
