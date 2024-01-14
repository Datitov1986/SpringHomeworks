package homeworks.spring.homework1;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class StudentRepository {
    private static List<Student> students;

    public StudentRepository() {
        this.students = new ArrayList<>();
        students.add(new Student("Ivan","Fiksiki"));
        students.add(new Student("Ignat","Fiksiki"));
        students.add(new Student("Elena","Smurfs"));
        students.add(new Student("Olga","Smeshariki"));
        students.add(new Student("Eugeny","Smurfs"));
        students.add(new Student("Oksana","Smeshariki"));
        students.add(new Student("Anton","Fiksiki"));
        students.add(new Student("Anton","OP-12-98"));
    }

    public void createStudent(Student student) {
        students.add(student);
    }

    public void deleteStudent(long id) {
        students.removeIf(student -> student.getId() == id);
    }

    public List<Student> getAll() {
        return List.copyOf(students);
    }

    public Student getByName(String name) {
        return students.stream()
                .filter(it -> Objects.equals(it.getName(), name))
                .findFirst()
                .orElse(null);
    }

    public Student getByGroup(String groupName) {
        return students.stream()
                .filter(it -> Objects.equals(it.getGroupName(), groupName))
                .findAny()
                .orElse(null);
    }

    public List<Student> getByGroupAll(String groupName) {
        return students.stream()
                .filter(it -> Objects.equals(it.getGroupName(), groupName))
                .collect(Collectors.toList());
    }

    public Student getById(long id) {
        return students.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findAny()
                .orElse(null);
    }

}
