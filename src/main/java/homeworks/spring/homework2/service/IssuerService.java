package homeworks.spring.homework2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import homeworks.spring.homework2.api.IssueRequest;
import homeworks.spring.homework2.model.Issue;
import homeworks.spring.homework2.repository.BookRepository;
import homeworks.spring.homework2.repository.IssueRepository;
import homeworks.spring.homework2.repository.ReaderRepository;
import org.springframework.web.server.ResponseStatusException;

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


}
