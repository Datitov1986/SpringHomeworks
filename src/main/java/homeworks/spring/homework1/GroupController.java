package homeworks.spring.homework1;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {
    private final StudentRepository repository;

    public GroupController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "{groupName}")
    public Student getStudentByGroup(@PathVariable String groupName) {
        return repository.getByGroup(groupName);
    }

    @GetMapping("/student")
    public List<Student> getStudents() {
        return repository.getAll();
    }
}
