package homeworks.spring.homework10.api;

import homeworks.spring.homework10.model.Book;
import homeworks.spring.homework10.model.Reader;
import homeworks.spring.homework10.repository.ReaderRepository;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebClient
public class ReaderControllerTest {
    @Autowired
    WebTestClient webTestClient;
    @Autowired
    ReaderRepository readerRepository;

    @Data
    @AllArgsConstructor
    static class JUnitReaderResponce {
        private Long id;
        private String name;
    }

    @Test
    void testFindByIdNotFound() {
        webTestClient.get()
                .uri("/book/-1")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testSave() {
        Reader request = new Reader(1L, "randomReader");


        JUnitReaderResponce responseBody = webTestClient.post()
                .uri("/reader")
                .bodyValue(request)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(JUnitReaderResponce.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody, "Responce Body is null");
        Assertions.assertNotNull(responseBody.getId(), "ID id null");
        Assertions.assertEquals(request.getId(), responseBody.getId(), "Id missmatch");
        Assertions.assertEquals(request.getName(), responseBody.getName(), "name missmatch");

        Assertions.assertTrue(readerRepository.findById(request.getId()).isPresent(), "reader not found in repository");
    }

    @Test
    void testFindByIdSuccess() {
        Reader expected = readerRepository.save(Reader.ofName("randomReader"));

        ReaderControllerTest.JUnitReaderResponce responceBody = webTestClient.get()
                .uri("/reader/" + expected.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(ReaderControllerTest.JUnitReaderResponce.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responceBody);
        Assertions.assertEquals(expected.getId(), responceBody.getId());
        Assertions.assertEquals(expected.getName(), responceBody.getName());
    }

    @Test
    void testGetAll() {
        readerRepository.saveAll(List.of(
                Reader.ofName("firstReader"),
                Reader.ofName("secondReader")
        ));

        List<Reader> expected = readerRepository.findAll();

        List<ReaderControllerTest.JUnitReaderResponce> responceBody = webTestClient.get()
                .uri("/reader/all")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<ReaderControllerTest.JUnitReaderResponce>>() {
                })
                .returnResult()
                .getResponseBody();

        Assertions.assertEquals(expected.size(), responceBody.size());
        for (ReaderControllerTest.JUnitReaderResponce readerResponce : responceBody) {
            boolean found = expected.stream()
                    .filter(it -> Objects.equals(it.getId(), readerResponce.getId()))
                    .anyMatch(it -> Objects.equals(it.getName(), readerResponce.getName()));
            Assertions.assertTrue(found);
        }
    }


}
