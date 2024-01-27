package homeworks.spring.homework4.service;

import homeworks.spring.homework4.api.IssueRequest;
import homeworks.spring.homework4.model.Book;
import homeworks.spring.homework4.model.Issue;
import homeworks.spring.homework4.model.Reader;
import homeworks.spring.homework4.repository.BookRepository;
import homeworks.spring.homework4.repository.IssueRepository;
import homeworks.spring.homework4.repository.ReaderRepository;
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
        if (bookRepository.findById(request.getBookId()).get() == null) {
            throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
        }
        if (readerRepository.findById(request.getReaderId()).get() == null) {
            throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
        }
        if (issueRepository.findById(request.getReaderId()).get() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Читатель уже имеет на руках книгу");
        }
        // можно проверить, что у читателя нет книг на руках (или его лимит не превышает в Х книг)

        Issue issue = new Issue(request.getBookId(), request.getReaderId());
        issueRepository.save(issue);
        return issue;
    }

    public Issue findIssueById(long id) {
        return issueRepository.findById(id).get();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).get();
    }

    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    public Reader getReaderById(Long id) {
        return readerRepository.findById(id).get();
    }



}
