package com.todo_list.todo_list_my_artifact.initializer;

import com.todo_list.todo_list_my_artifact.dao.UserRoleDao;
import com.todo_list.todo_list_my_artifact.models.RoleType;
import com.todo_list.todo_list_my_artifact.models.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements ApplicationRunner {

    @Autowired
    UserRoleDao userRoleDao;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if( userRoleDao.count()==0) {
            for(RoleType roleType: RoleType.values()) {

                RoleType role = RoleType.getOne_byDisplayName(roleType.getDisplayeName());
                userRoleDao.save( new UserRole( role ) );
            }

        }

    }
}
