package com.cydeo.bootstrap;

import com.cydeo.dto.RoleDTO;
import org.springframework.boot.CommandLineRunner;

public class DataGenerator implements CommandLineRunner {

// note: as we don't have a DB layer right now, although the role object needs to be saved in the DB which must be entity object itself, we use dto objects.
    @Override
    public void run(String... args) throws Exception {

        RoleDTO adminRole = new RoleDTO(1L,"Admin");
        RoleDTO managerRole = new RoleDTO(2L,"Manager");
        RoleDTO employeeRole = new RoleDTO(3L,"Employee");

    }
}
