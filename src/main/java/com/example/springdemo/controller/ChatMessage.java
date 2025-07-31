package com.example.springdemo.controller;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class ChatMessage {
    private static final String TIME_FORMATTER = "HH:mm:ss";
    private String from;
    private String text;
    private String recipient;
    private String time;

    public ChatMessage(String from, String text, String recipient) {
        this.from = from;
        this.text = text;
        this.recipient = recipient;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMATTER);
        this.time = LocalDateTime.now().format(formatter);
    }
}
