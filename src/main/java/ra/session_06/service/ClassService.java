package ra.session_06.service;

import org.springframework.stereotype.Service;
import ra.session_06.repository.ClassRepository;
import ra.session_06.entity.Class;

import java.util.List;
import java.util.Optional;

@Service
public class ClassService {

    private final ClassRepository classRepository;

    public ClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    public List<Class> getAll() {
        return classRepository.findAll();
    }

    public Optional<Class> getById(Long id) {
        return classRepository.findById(id);
    }

    public Class save(Class classEntity) {
        return classRepository.save(classEntity);
    }

    public Class update(Long id, Class updated) {
        return classRepository.findById(id).map(c -> {
            c.setClassName(updated.getClassName());
            c.setStatus(updated.getStatus());
            return classRepository.save(c);
        }).orElseThrow(() -> new RuntimeException("Class not found"));
    }

    public void delete(Long id) {
        classRepository.deleteById(id);
    }
}