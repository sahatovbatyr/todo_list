package com.todo_list.todo_list_my_artifact.initializer;

import com.todo_list.todo_list_my_artifact.config.YamlConfig;
import com.todo_list.todo_list_my_artifact.models.RoleType;

import com.todo_list.todo_list_my_artifact.models.UserRole;
import com.todo_list.todo_list_my_artifact.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

@Component
public class DataInitializer implements ApplicationRunner {

    @Autowired
    private YamlConfig yamlConfig;

    @Autowired
    UserRoleService userRoleService;

    @Override
    public void run(ApplicationArguments args) throws Exception {


        System.out.println("YAML Properties " + yamlConfig.toString());

        if(userRoleService.getAll().isEmpty()) {
            for(RoleType iRoleType: RoleType.values()) {

                UserRole newRole = new UserRole();
                newRole.setRoleType( iRoleType );

                userRoleService.create( newRole  );
            }

//            Arrays.stream(RoleType.values())
//                    .map( r-> { var u= new UserRole( ); u.setRoleType(r); return u;  } )
//                    .collect(Collectors.toList());

        }


    }
}
