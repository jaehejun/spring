package com.example.springdemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class MessageController {
    private final MessageSource messageSource;
    @GetMapping("/welcome")
    public ResponseEntity<Object> welcome(String name) {
        System.out.println(LocaleContextHolder.getLocale());
        return ResponseEntity.ok(
                messageSource.getMessage(
                        // message code (message.propoerties에 작성된 key)
                        "welcome",
                        // arguments
                        new String[] {name},
                        //header의 Accept-Language값을 이용해 메세지 처리할 언어 선택
                        //값이 ko,ko_KR이면 messages_ko.properties,
                        //값이 en,en_US이면 messages)en.properties 활용
                        LocaleContextHolder.getLocale()
                )
        );
    }
}
