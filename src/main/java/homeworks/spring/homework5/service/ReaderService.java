package homeworks.spring.homework5.service;

import homeworks.spring.homework5.model.Issue;
import homeworks.spring.homework5.model.Reader;
import homeworks.spring.homework5.repository.IssueRepository;
import homeworks.spring.homework5.repository.ReaderRepository;
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
