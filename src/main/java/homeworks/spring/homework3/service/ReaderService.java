package homeworks.spring.homework3.service;

import homeworks.spring.homework3.model.Issue;
import homeworks.spring.homework3.repository.IssueRepository;
import homeworks.spring.homework3.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReaderService {
    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;

    public Issue getIssuesForReader(long readerId) {
        return issueRepository.findByReaderId(readerId);
    }
}
