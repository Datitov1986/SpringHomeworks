package homeworks.spring.homework3.repository;

import homeworks.spring.homework3.model.Issue;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class IssueRepository {

    private final List<Issue> issues;


    public IssueRepository() {
        this.issues = new ArrayList<>();
    }

    public void save(Issue issue) {
        // insert into ....
        issues.add(issue);
    }

    public Issue getIssueById(long id) {
        return issues.stream().filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public Issue findByReaderId(long id) {
        return issues.stream().filter(it -> Objects.equals(it.getReaderId(), id))
                .findFirst()
                .orElse(null);
    }
    public List<Issue> getAllIssues() {
        return List.copyOf(issues);
    }


}
