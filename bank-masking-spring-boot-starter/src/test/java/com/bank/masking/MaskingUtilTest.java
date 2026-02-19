package com.bank.masking;

import com.bank.masking.core.MaskStyle;
import com.bank.masking.core.MaskingUtil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MaskingUtilTest {

    @Test
    void shouldMaskLast4() {
        String masked = MaskingUtil.mask("123456789", MaskStyle.LAST4, "*");
        assertEquals("*****6789", masked);
    }
}
