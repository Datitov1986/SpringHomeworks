package homeworks.spring.homework1;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/all")
    public List<Student> getStudents() {
        return repository.getAll();
    }

    @GetMapping(value = "/{id}")
    public Student getStudent(@PathVariable long id) {
        return repository.getById(id);
    }

    @GetMapping
    public Student getStudentByName(@RequestParam String name) {
        return repository.getByName(name);
    }

    @GetMapping("/group/{groupName}/student")
    public List<Student> getStudentInGroup(@PathVariable String groupName) {
        return repository.getByGroupAll(groupName);
    }

    @PatchMapping("/{id}")
    public Student updateStudent(@PathVariable long id, @RequestBody Student student) {
        Student existsStudent = repository.getById(id);
        if (existsStudent == null) {
            throw new IllegalArgumentException();
        }

        existsStudent.setName(student.getName());
        return existsStudent;
    }

    @PostMapping()
    public void createStudent(@RequestBody Student student) {
        repository.createStudent(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable long id) {
        repository.deleteStudent(id);
    }
}
