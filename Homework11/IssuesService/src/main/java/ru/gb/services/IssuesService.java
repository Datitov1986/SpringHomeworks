package ru.gb.services;


import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import ru.gb.*;
import ru.gb.controllers.IssuesRequest;
import ru.gb.repositories.IssuesRepository;

import java.time.LocalDateTime;
import java.util.*;

@Service
@EnableConfigurationProperties(ReaderProperties.class)
public class IssuesService {
    private final IssuesRepository issuanceRepository;
    private final ReaderProperties maxIssuedBooks;
    private final WebClient webClient;


    public IssuesService(IssuesRepository issuanceRepository, ReaderProperties maxIssuedBooks,
                         ReactorLoadBalancerExchangeFilterFunction loadBalancerExchangeFilterFunction) {
        this.issuanceRepository = issuanceRepository;
        this.maxIssuedBooks = maxIssuedBooks;
        this.webClient = WebClient.builder()
                .filter(loadBalancerExchangeFilterFunction)
                .build();
    }


    /**
     * Первоначальные тестовые данные
     */
    @PostConstruct
    void generateData() {
        Random random = new Random();
        List<Issuance> issuanceList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            long idReader = random.nextLong(10) + 1;
            if (issuanceList.stream().filter(it -> it.getReaderId().equals(idReader)).count() != maxIssuedBooks.getMaxAllowedBooks()) {
                issuanceList.add(new Issuance(random.nextLong(20) + 1, idReader));
            } else {
                i--;
            }
        }
        issuanceRepository.saveAll(issuanceList);
        returnBookByReader(getIssuanceById(random.nextLong(20) + 1));
        returnBookByReader(getIssuanceById(random.nextLong(20) + 1));
        returnBookByReader(getIssuanceById(random.nextLong(20) + 1));
    }

    /**
     * Метод проверяет получение списка всех выдач книг
     *
     * @return если список не пуст, то метод возвращает список всех выдач книг, иначе исключение
     */
    public List<IssuanceDTO> getIssuanceList() {
        List<Issuance> issuanceList = issuanceRepository.findAll();
        if (issuanceList.isEmpty()) {
            throw new NullPointerException("Книги не кому не выдавались");
        }
        return getIssuanceTransforms(issuanceList);
    }

    private List<IssuanceDTO> getIssuanceTransforms(List<Issuance> issuanceList) {
        List<IssuanceDTO> issuanceTransformList = new ArrayList<>();
        try {
            for (Issuance issuance : issuanceList) {
                issuanceTransformList.add(createIssuanceDTO(issuance));
            }
        } catch (Exception e) {
            throw new RuntimeException("Соединение с сервером не установлено");
        }
        return issuanceTransformList;
    }

    /**
     * Метод обработки получения выдачи по ID
     *
     * @param id идентификатор выдачи
     * @return если выдач с ID найдена, то метод выведет выдачу, иначе исключение
     */
    public IssuanceDTO getIssuanceById(Long id) {
        Issuance issuance = issuanceRepository
                .findById(id)
                .orElseThrow(
                        () -> new NoSuchElementException("Выдача с ID = " + id + " не найдена")
                );
        IssuanceDTO issuanceTransform;
        try {
            issuanceTransform = createIssuanceDTO(issuance);
        } catch (Exception e) {
            throw new RuntimeException("Соединение с сервером не установлено");
        }
        return issuanceTransform;
    }

    /**
     * Метод поиска выдачи книг читателю по ID
     *
     * @param id идентификатор читателя
     * @return список всех выдач книг читателю c ID
     */
    public List<IssuanceDTO> getIssuanceByIdReader(Long id) {
        List<Issuance> issuanceList = issuanceRepository.findIssuanceByReaderId(id);
        if (issuanceList.isEmpty()) {
            throw new NoSuchElementException("Читателю с ID = " + id + " книги не выдавались");
        }
        return getIssuanceTransforms(issuanceList);
    }

    /**
     * Метод обрабатывает введенные данные пользователем
     * при выдаче книг читателю
     *
     * @param issuanceRequest данные введенные пользователем
     * @return если данные введенные пользователем корректны, то метод вернет информацию о выдаче книги читателю,
     * иначе исключение
     */
    public Issuance issuanceBook(IssuesRequest issuanceRequest) {
        Issuance issuance = new Issuance(issuanceRequest.getBookId(), issuanceRequest.getReaderId());
        issuanceRepository.save(issuance);
        return issuance;
    }

    /**
     * Метод проставляет дату возврата книги читателем, тем самым закрывает выдачу
     */
    public void returnBookByReader(IssuanceDTO issuanceTransform) {
        if (!Objects.isNull(issuanceTransform.getReturnedAt())) {
            throw new NoSuchElementException("Выдача с ID = " + issuanceTransform.getId() + " закрыта");
        }
        issuanceTransform.setReturnedAt(LocalDateTime.now());
        issuanceRepository.save(new Issuance().fromIssuanceTransform(issuanceTransform));
    }

    /**
     * Метод получение книги по ID через API
     *
     * @param id - идентификатор книги
     * @return Описание книги
     */
    private Book getBookByIdInApi(Long id) {
        return webClient.get()
                .uri("http://BOOK-SERVICE/book/" + id)
                .retrieve()
                .bodyToMono(Book.class)
                .block();
    }

    /**
     * Метод получение описание читателя по ID через API
     *
     * @param id - идентификатор читателя
     * @return описание читателя
     */
    private Reader getReaderByIdInApi(Long id) {
        return webClient.get()
                .uri("http://READER-SERVICE/reader/" + id)
                .retrieve()
                .bodyToMono(Reader.class)
                .block();
    }

    /**
     * Метод преобразования стандартной выдачи в выдачу с полным описанием
     *
     * @param issuance стандартная выдача
     * @return Выдача с полным описанием
     */
    public IssuanceDTO createIssuanceDTO(Issuance issuance) throws WebClientResponseException {
        IssuanceDTO issuanceTransform = new IssuanceDTO();
        issuanceTransform.setId(issuance.getId());
        issuanceTransform.setBook(getBookByIdInApi(issuance.getBookId()));
        issuanceTransform.setReader(getReaderByIdInApi(issuance.getReaderId()));
        issuanceTransform.setIssuanceAt(issuance.getIssuance_at());
        issuanceTransform.setReturnedAt(issuance.getReturned_at());
        return issuanceTransform;
    }
}
