package com.github.kay.mmall.infrasucture.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;


@Data
@ConfigurationProperties(prefix = "mmall.redisson")
public class RedissonProperties {
    private boolean enabled;
    private String fileName;
}
