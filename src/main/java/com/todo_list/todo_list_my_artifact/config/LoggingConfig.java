package com.todo_list.todo_list_my_artifact.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class LoggingConfig {

    //for WEB logging
    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter(){
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeHeaders(true);
        loggingFilter.setIncludeClientInfo( true);
        loggingFilter.setIncludePayload( true);

        return  loggingFilter;

    }
}
