package homeworks.spring.homework10.service;

import homeworks.spring.homework10.model.Issue;
import homeworks.spring.homework10.repository.IssueRepository;
import homeworks.spring.homework10.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReaderService {
    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;

    public Issue getIssuesForReader(long readerId) {
        return issueRepository.findById(readerId).get();
    }


}
