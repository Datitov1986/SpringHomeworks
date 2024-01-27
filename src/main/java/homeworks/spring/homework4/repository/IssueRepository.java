package homeworks.spring.homework4.repository;

import homeworks.spring.homework4.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {



}
