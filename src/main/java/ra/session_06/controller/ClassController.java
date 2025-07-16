package ra.session_06.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.session_06.entity.Class;
import ra.session_06.service.ClassService;

import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClassController {

    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @GetMapping
    public List<Class> getAll() {
        return classService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Class> getById(@PathVariable Long id) {
        return classService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Class create(@RequestBody Class classEntity) {
        return classService.save(classEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Class> update(@PathVariable Long id, @RequestBody Class updated) {
        try {
            return ResponseEntity.ok(classService.update(id, updated));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        classService.delete(id);
        return ResponseEntity.noContent().build();
    }
}