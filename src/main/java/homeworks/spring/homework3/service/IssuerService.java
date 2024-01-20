package homeworks.spring.homework3.service;

import homeworks.spring.homework3.api.IssueRequest;
import homeworks.spring.homework3.model.Book;
import homeworks.spring.homework3.model.Issue;
import homeworks.spring.homework3.model.Reader;
import homeworks.spring.homework3.repository.BookRepository;
import homeworks.spring.homework3.repository.IssueRepository;
import homeworks.spring.homework3.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class IssuerService {

    // спринг это все заинжектит
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;

    public Issue issue(IssueRequest request) {
        if (bookRepository.getBookById(request.getBookId()) == null) {
            throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
        }
        if (readerRepository.getReaderById(request.getReaderId()) == null) {
            throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
        }
        if (issueRepository.findByReaderId(request.getReaderId()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Читатель уже имеет на руках книгу");
        }
        // можно проверить, что у читателя нет книг на руках (или его лимит не превышает в Х книг)

        Issue issue = new Issue(request.getBookId(), request.getReaderId());
        issueRepository.save(issue);
        return issue;
    }

    public Issue findIssueById(long id) {
        return issueRepository.getIssueById(id);
    }

    public Book getBookById(Long id) {
        return bookRepository.getBookById(id);
    }

    public List<Issue> getAllIssues() {
        return issueRepository.getAllIssues();
    }

    public Reader getReaderById(Long id) {
        return readerRepository.getReaderById(id);
    }



}
