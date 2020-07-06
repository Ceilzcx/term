package com.example.term.utils;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "file")
@PropertySource("classpath:upload.properties")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UploadBean {
    String rootPath;
    String urlPath;
}
