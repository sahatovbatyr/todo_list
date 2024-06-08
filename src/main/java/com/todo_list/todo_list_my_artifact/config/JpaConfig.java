package com.todo_list.todo_list_my_artifact.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "com.todo_list" )
@EnableJpaRepositories(basePackages = "com.todo_list.todo_list_my_artifact.dao"
//        includeFilters = {
//                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = UserDao.class),
//                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = UserRoleTempl.class)
//
//        }
)
public class JpaConfig {

}
