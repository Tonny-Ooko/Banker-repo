package com.bank.masking.core;

public final class MaskingUtil {

    private MaskingUtil() {}

    public static String mask(String value, MaskStyle style, String maskChar) {
        if (value == null) return null;

        return switch (style) {
            case FULL -> maskChar.repeat(value.length());
            case LAST4 -> maskChar.repeat(Math.max(0, value.length() - 4))
                    + value.substring(Math.max(0, value.length() - 4));
            case PARTIAL -> value.substring(0, Math.min(2, value.length()))
                    + maskChar.repeat(Math.max(0, value.length() - 2));
        };
    }
}
