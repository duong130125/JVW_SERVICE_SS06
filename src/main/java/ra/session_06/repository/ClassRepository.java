package ra.session_06.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.session_06.entity.Class;

public interface ClassRepository extends JpaRepository<Class, Long> {

}