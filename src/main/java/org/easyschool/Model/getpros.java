package org.easyschool.Model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
@ConfigurationProperties(prefix = "eazyschool")
@Validated
@Component
public class getpros {

    private int pageSize;
}
