package ra.session_06.service;

import org.springframework.stereotype.Service;
import ra.session_06.entity.Student;
import ra.session_06.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
       return studentRepository.findAll();
    }

    public Student findById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public Student update(Student student) {
        return studentRepository.findById(student.getStuId())
                .map(existingStudent -> {
                    existingStudent.setFullName(student.getFullName());
                    existingStudent.setGender(student.getGender());
                    existingStudent.setBirthday(student.getBirthday());
                    existingStudent.setAddress(student.getAddress());
                    existingStudent.setClassEntity(student.getClassEntity());
                    return studentRepository.save(existingStudent);
                })
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + student.getStuId()));
    }

    public void delete(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }
}
