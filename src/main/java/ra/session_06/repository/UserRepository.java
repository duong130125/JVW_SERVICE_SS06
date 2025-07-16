package ra.session_06.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.session_06.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}