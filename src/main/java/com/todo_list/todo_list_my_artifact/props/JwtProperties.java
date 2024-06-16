package com.todo_list.todo_list_my_artifact.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "token.jwt")
public class JwtProperties {
    private String secret;
    private Duration accessPeriod;
    private Duration refreshPeriod;
}
