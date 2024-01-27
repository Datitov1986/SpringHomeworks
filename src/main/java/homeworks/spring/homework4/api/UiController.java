package homeworks.spring.homework4.api;

import homeworks.spring.homework4.model.*;
import homeworks.spring.homework4.repository.BookRepository;
import homeworks.spring.homework4.repository.IssueRepository;
import homeworks.spring.homework4.repository.ReaderRepository;
import homeworks.spring.homework4.service.IssuerService;
import homeworks.spring.homework4.service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UiController {
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;
    private final IssuerService issuerService;
    private final ReaderService readerService;

    @GetMapping("/ui/books")
    public String getAllBooks(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "bookList";
    }

    @GetMapping("/ui/reader")
    public String getAllReaders(Model model) {
        List<Reader> readers = readerRepository.findAll();
        model.addAttribute("readers", readers);
        return "readerList";
    }

    @GetMapping("/ui/issues")
    public String getAllIssues(Model model) {
        List<Issue> issues = issueRepository.findAll();
        List<IssueDto> issueDtos = new ArrayList<>();
        for(Issue issue: issues) {
            String bookName = issuerService.getBookById(issue.getBookId()).getName();
            String readerName = issuerService.getReaderById(issue.getReaderId()).getName();
            LocalDateTime timestamp = issuerService.findIssueById(issue.getId()).getTimestamp();
            IssueDto issueDto = new IssueDto(issue.getId(),bookName,readerName,timestamp);
            issueDtos.add(issueDto);
        }
        model.addAttribute("issues", issueDtos);
        return "issueList";
    }

    @GetMapping("/ui/reader/{id}")
    public String getReaderDetails(@PathVariable long id, Model model) {
        Reader reader = readerRepository.getReferenceById(id);
        Issue readerIssue = readerService.getIssuesForReader(reader.getId());
        String bookName = issuerService.getBookById(readerIssue.getBookId()).getName();
        ReaderDto readerDto = new ReaderDto(id, bookName,reader.getName(),readerIssue);
        model.addAttribute("reader", readerDto);
        return "readerDetails";
    }

}
