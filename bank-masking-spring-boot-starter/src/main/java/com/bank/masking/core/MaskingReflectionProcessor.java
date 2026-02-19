package com.bank.masking.core;

import com.bank.masking.config.MaskingProperties;
import com.bank.masking.annotation.Mask;

import java.lang.reflect.Field;
import java.util.*;

public final class MaskingReflectionProcessor {

    private MaskingReflectionProcessor() {}

    public static Object process(Object source, MaskingProperties props) {
        if (source == null) return null;

        try {
            Object copy = source.getClass().getDeclaredConstructor().newInstance();

            for (Field field : source.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(source);

                if (value == null) {
                    field.set(copy, null);
                    continue;
                }

                boolean shouldMask =
                        field.isAnnotationPresent(Mask.class)
                        || props.getFields().contains(field.getName());

                if (shouldMask && value instanceof String) {
                    field.set(copy,
                            MaskingUtil.mask(
                                    (String) value,
                                    props.getMaskStyle(),
                                    props.getMaskCharacter()
                            )
                    );
                } else if (isComplexObject(value)) {
                    field.set(copy, process(value, props));
                } else {
                    field.set(copy, value);
                }
            }
            return copy;
        } catch (Exception e) {
            return source; // fail-safe: never break logging
        }
    }

    private static boolean isComplexObject(Object obj) {
        return !(obj instanceof String
                || obj instanceof Number
                || obj instanceof Boolean
                || obj.getClass().isEnum());
    }
}
