package ra.session_06.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.session_06.entity.Student;
import ra.session_06.repository.ClassRepository;
import ra.session_06.repository.StudentRepository;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository studentRepo;
    private final ClassRepository classRepo;

    public StudentController(StudentRepository studentRepo, ClassRepository classRepo) {
        this.studentRepo = studentRepo;
        this.classRepo = classRepo;
    }

    @GetMapping
    public List<Student> getAll() {
        return studentRepo.findAll();
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student) {
        if (student.getClassEntity() != null) {
            classRepo.findById(student.getClassEntity().getClassId()).ifPresent(student::setClassEntity);
        }
        return ResponseEntity.ok(studentRepo.save(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student updated) {
        return studentRepo.findById(id)
                .map(student -> {
                    student.setFullName(updated.getFullName());
                    student.setGender(updated.getGender());
                    student.setAddress(updated.getAddress());
                    student.setBirthday(updated.getBirthday());
                    if (updated.getClassEntity() != null) {
                        classRepo.findById(updated.getClassEntity().getClassId())
                                .ifPresent(student::setClassEntity);
                    }
                    return ResponseEntity.ok(studentRepo.save(student));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}