package homeworks.spring.homework4.service;

import homeworks.spring.homework4.model.Issue;
import homeworks.spring.homework4.repository.IssueRepository;
import homeworks.spring.homework4.repository.ReaderRepository;
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
