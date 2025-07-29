package com.example.springdemo.entity;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

@Component
public class GenericBigDecimalConverter implements GenericConverter {
    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Set.of(
                new ConvertiblePair(Number.class, BigDecimal.class),
                new ConvertiblePair(String.class, BigDecimal.class)
        );
    }
    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (source instanceof String str) return new BigDecimal(str);
        if (source instanceof Number num) return new BigDecimal(num.doubleValue()).setScale(2, RoundingMode.HALF_EVEN);
        return source;
    }
}
