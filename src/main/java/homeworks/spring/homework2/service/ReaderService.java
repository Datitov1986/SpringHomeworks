package homeworks.spring.homework2.service;

import homeworks.spring.homework2.model.Issue;
import homeworks.spring.homework2.repository.IssueRepository;
import homeworks.spring.homework2.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderService {
    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;

    public Issue getIssuesForReader(long readerId) {
        return issueRepository.findByReaderId(readerId);
    }
}
