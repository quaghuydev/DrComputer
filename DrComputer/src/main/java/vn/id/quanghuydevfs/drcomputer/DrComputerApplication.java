package vn.id.quanghuydevfs.drcomputer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import vn.id.quanghuydevfs.drcomputer.dto.auth.RegisterDto;
import vn.id.quanghuydevfs.drcomputer.service.AuthService;

import static vn.id.quanghuydevfs.drcomputer.model.user.Roles.ADMIN;
import static vn.id.quanghuydevfs.drcomputer.model.user.Roles.MANAGER;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class DrComputerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DrComputerApplication.class, args);
    }
//    @Bean
//    public CommandLineRunner commandLineRunner(
//            AuthService service
//    ) {
//        return args -> {
//            var admin = RegisterDto.builder()
//                    .fullname("Admin")
//                    .email("admin@mail.com")
//                    .password("password")
//                    .role(ADMIN)
//                    .build();
//            System.out.println("Admin token: " + service.register(admin).getAccessToken());
//
//            var manager = RegisterDto.builder()
//                    .fullname("Admin")
//                    .email("manager@mail.com")
//                    .password("password")
//                    .role(MANAGER)
//                    .build();
//            System.out.println("Manager token: " + service.register(manager).getAccessToken());
//
//        };
//    }
}
