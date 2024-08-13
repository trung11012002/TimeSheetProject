package com.timesheet.timesheetproject.config;

import com.timesheet.timesheetproject.entity.Role;
import com.timesheet.timesheetproject.entity.User;
import com.timesheet.timesheetproject.repository.RoleRepository;
import com.timesheet.timesheetproject.repository.UserRepository;
import com.timesheet.timesheetproject.service.IUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
public class ApplicationInitConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepostitory;
    @Autowired
    private IUserRoleService roleService;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            // Check if admin user already exists
            if (userRepository.findByUsername("admin").isEmpty()) {
                // Create admin user
                User adminUser = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .build();

                // Create role ADMIN if not exists
                Role adminRole = roleRepostitory.findByCode("ADMIN")
                        .orElseGet(() -> {
                            Role newAdminRole = Role.builder()
                                    .code("ADMIN")
                                    .name("Admin")
                                    .build();
                            return roleRepostitory.save(newAdminRole);
                        });
                // Create userRole for account admin

                userRepository.save(adminUser);

                log.warn("Admin user has been created with default password: admin, please change it");
            }
        };
    }
}
