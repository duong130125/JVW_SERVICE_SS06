package ra.session_06.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.session_06.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
