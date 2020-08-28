package vn.javisco.agency.seeders;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import vn.javisco.agency.entity.UserLogin;
import vn.javisco.agency.entity.UserRoleFlag;
import vn.javisco.agency.entity.UserStatusFlag;
import vn.javisco.agency.repository.UserRepository;

@Component
public class DatabaseSeeder {

    private final UserRepository userRepository;

    public DatabaseSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedUsersTable();
    }

    private void seedUsersTable() {
        if (userRepository.count() == 0) {
            UserLogin user = new UserLogin();
            user.setName("Admin");
            user.setEmail("admin@javisco.vn");
            user.setRoleFlag(UserRoleFlag.ROLE_ADMIN);
            user.setStatusFlag(UserStatusFlag.ACTIVE);
            user.setPassword(new BCryptPasswordEncoder().encode("123456"));
            userRepository.save(user);
        }
    }
}