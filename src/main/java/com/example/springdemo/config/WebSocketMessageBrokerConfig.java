package com.example.springdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketMessageBrokerConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 구동(sub) : 접두사로 시작하는 메시지를 브로커가 처리하도록 설정
        // 클라이언트는 이 접두사로 시작하는 주제를 구독하여 메시지를 수신
        // 소켓 통신에서 사용자가 특정 메시지를 받기 위해 "/sub"이라는 prefix 기반 메시지 수신을 위해 Subscribe
        config.enableSimpleBroker("/topic");

        // regesterStompEndpoints() :
        // 각각 특정 URL에 매핑되는 STOMP 엔드포인트를 등록하고
        // 선택적으로 SockJS fallback 옵션을 활성화하고 구성
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 클라이언트가 WebSocket에 연결하기 위한 엔드포인트를 "/broadcast"로 설정
        registry.addEndpoint("/broadcast");
        // WebSocket을 지원하지 않는 브라우저에서도 SockJS를 통해 WebSocket 기능을 사용 가능하게 함
        // custom heartbeat, every 60 sec
        registry.addEndpoint("/broadcast").withSockJS().setHeartbeatTime(60_000);
    }
}
