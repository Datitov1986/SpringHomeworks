package homeworks.spring.homework5.api;

import homeworks.spring.homework5.model.Issue;
import homeworks.spring.homework5.repository.IssueRepository;
import homeworks.spring.homework5.service.IssuerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/issue")
@Tag(name = "Записи выдачи")
public class IssuerController {

    @Autowired
    private IssuerService service;
    private final IssueRepository issueRepository;

    public IssuerController(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

//  @PutMapping
//  public void returnBook(long issueId) {
//    // найти в репозитории выдачу и проставить ей returned_at
//  }

    @PostMapping
    public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest request) {
        log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());

        final Issue issue;
        try {
            issue = service.issue(request);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(issue);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Issue> getIssueById(@PathVariable long id) {
        Issue issue = service.findIssueById(id);
        if (issue != null) {
            return ResponseEntity.ok(issue);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Issue> findAllIssues() {
        return issueRepository.findAll();
    }

}
