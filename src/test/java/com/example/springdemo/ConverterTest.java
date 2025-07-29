package com.example.springdemo;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
@Log4j2
@SpringBootTest
public class ConverterTest {

    @Autowired
    private ConversionService conversionService; // 스프링이 주입

    @Test
    public void whenConvertStringToIntegerUsingDfaultConverter_thenSuccess() {
        assertThat(conversionService.convert("25",Integer.class)).isEqualTo(25);
    }

    @Test
    void whenConvertingToBigDecimalUsingGenericConverter_thenSuccess() {

        BigDecimal v1 = conversionService.convert(11,BigDecimal.class);
        log.info("11 -> {}",v1);
        assertThat(v1).isEqualTo(BigDecimal.valueOf(11.00)
                .setScale(2,BigDecimal.ROUND_HALF_EVEN));

        BigDecimal v2 = conversionService.convert(25.23,BigDecimal.class);
        log.info("25.23 -> {}",v2);
        assertThat(v2).isEqualTo(BigDecimal.valueOf(25.23));

        BigDecimal v3 = conversionService.convert("2.32",BigDecimal.class);
        log.info("\"2.32\" -> {}",v3);
        assertThat(v3).isEqualTo(BigDecimal.valueOf(2.32));


    }
}
