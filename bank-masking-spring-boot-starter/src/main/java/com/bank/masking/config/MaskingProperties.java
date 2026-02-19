package com.bank.masking.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "p11.masking")
@Getter
@Setter
public class MaskingProperties {
    private boolean enabled;
    private List<String> fields = new ArrayList<>();
    private MaskStyle maskStyle = MaskStyle.PARTIAL;
    private String maskCharacter = "*";
}
