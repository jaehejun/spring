package com.example.springdemo.util;

import ch.qos.logback.classic.spi.ILoggingEvent;
import org.springframework.boot.logging.structured.StructuredLogFormatter;

public class MyCustomFormat implements StructuredLogFormatter<ILoggingEvent> {
    @Override
    public String format(ILoggingEvent event) {
        return "time=" + event.getInstant()
                + " level=" + event.getLevel()
                + " message=" + event.getMessage() + "\n";
    }
}


//package com.example.springdemo.util;
//
//import org.apache.logging.log4j.core.LogEvent;
//import org.springframework.boot.logging.structured.StructuredLogFormatter;
//
//public class MyCustomFormat implements StructuredLogFormatter<LogEvent> {
//    @Override
//    public String format(LogEvent event) {
//        return "time=" + event.getTimeMillis() +
//                " level=" + event.getLevel() +
//                " message=" + event.getMessage().getFormattedMessage() + "\n";
//    }
//}
