package com.timesheet.timesheetproject.config;

import com.timesheet.timesheetproject.entity.Role;
import com.timesheet.timesheetproject.entity.User;
import com.timesheet.timesheetproject.mapper.UserMapper;
import com.timesheet.timesheetproject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
@Slf4j
@Configuration
public class ApplicationInitConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;
    ApplicationRunner applicationRunner(UserRepository userRepository){
        return args -> {
            Role role = new Role();
            if(userRepository.findByUsername("admin").isEmpty()){
                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .build();
                user.setRole(role);
                userRepository.save(user);
                log.warn("Admin user has been created with default password: admin, please change it");
            }

        };
    }
}
