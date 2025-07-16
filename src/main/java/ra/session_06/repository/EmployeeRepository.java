package ra.session_06.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.session_06.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}