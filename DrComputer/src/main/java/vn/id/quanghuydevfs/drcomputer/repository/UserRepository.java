package vn.id.quanghuydevfs.drcomputer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.id.quanghuydevfs.drcomputer.model.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User>findByEmail(String email);

}
