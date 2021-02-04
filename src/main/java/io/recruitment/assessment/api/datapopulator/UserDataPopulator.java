package io.recruitment.assessment.api.datapopulator;

import io.recruitment.assessment.api.domain.User;
import io.recruitment.assessment.api.enumeration.UserType;
import io.recruitment.assessment.api.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UserDataPopulator implements CommandLineRunner {

    private final UserRepository userRepository;

    public UserDataPopulator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        List<User> existingUsers = userRepository.findAll();
        if (existingUsers.isEmpty()) {
            // add admin user
            User adminUser = new User()
                    .setUsername("admin")
                    .setPassword("admin")
                    .setName("admin")
                    .setUserType(UserType.ADMIN);
            userRepository.save(adminUser);

            // add customer user
            User customerUser = new User()
                    .setUsername("customer")
                    .setPassword("customer")
                    .setName("customer")
                    .setUserType(UserType.CUSTOMER);
            userRepository.save(customerUser);
        }
    }
}
