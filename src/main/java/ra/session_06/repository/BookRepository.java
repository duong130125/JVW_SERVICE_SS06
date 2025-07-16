package ra.session_06.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.session_06.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}