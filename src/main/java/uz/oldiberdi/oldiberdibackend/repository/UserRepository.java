package uz.oldiberdi.oldiberdibackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.oldiberdi.oldiberdibackend.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhoneNumber(String phoneNumber);
    Optional<User> findById(Long id);
}
