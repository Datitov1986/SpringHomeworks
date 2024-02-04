package homeworks.spring.homework5_2.repository;

import homeworks.spring.homework5_2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
}
