package my.study.webflux.controller;

import my.study.webflux.domain.User;
import my.study.webflux.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

/**
 * Created by leesangpil on 2018. 9. 3..
 */
@RunWith(SpringRunner.class)
@WebFluxTest(UserController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class UserControllerTests {

    public static final String TEST_ID_1 = "5b8ba63389bcc71fdca1158b";

    public static final String TEST_NICKNAME_1 = "testNickname1";
    public static final String TEST_NICKNAME_2 = "testNickname2";

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    UserService userService;

    @Test
    public void getAllUsers() {
        // given
        User testUser1 = User.create(TEST_NICKNAME_1);
        User testUser2 = User.create(TEST_NICKNAME_2);
        // when
        when(userService.findAll()).thenReturn(Flux.just(testUser1, testUser2));
        // then
        webTestClient.get().uri("/users/")
                .exchange()
                .expectStatus().isOk().expectBodyList(User.class).hasSize(2)
                .consumeWith(document("getAllUsers"));
    }

    @Test
    public void getUser() {
        // given
        User testUser1 = User.create(TEST_NICKNAME_1);
        // when
        when(userService.findOne(TEST_ID_1)).thenReturn(Mono.just(testUser1));
        // then
        webTestClient.get().uri("/users/{id}", TEST_ID_1)
                .exchange()
                .expectStatus().isOk().expectBody()
                .jsonPath("$.nickname").isNotEmpty()
                .jsonPath("$.nickname").isEqualTo(TEST_NICKNAME_1)
                .consumeWith(document("getUser"));
    }

    @Test
    public void createUser() {
        // given
        User testUser1 = User.create(TEST_NICKNAME_1);
        // when
        when(userService.createUser(TEST_NICKNAME_1)).thenReturn(Mono.just(testUser1));
        // then
        webTestClient.post().uri("/users/{nickname}", TEST_NICKNAME_1)
                .exchange()
                .expectStatus().isOk().expectBody()
                .jsonPath("$.nickname").isNotEmpty()
                .jsonPath("$.nickname").isEqualTo(TEST_NICKNAME_1)
                .consumeWith(document("createUser"));
    }

    @Test
    public void updateNickname() {
        // given
        User testUser1 = User.create(TEST_NICKNAME_1);
        testUser1.setId(TEST_ID_1);
        // when
        when(userService.updateNickname(TEST_ID_1, TEST_NICKNAME_1)).thenReturn(Mono.just(testUser1));
        // then
        webTestClient.patch().uri("/users/{id}/{nickname}", TEST_ID_1, TEST_NICKNAME_1)
                .exchange()
                .expectStatus().isOk().expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.id").isEqualTo(TEST_ID_1)
                .jsonPath("$.nickname").isNotEmpty()
                .jsonPath("$.nickname").isEqualTo(TEST_NICKNAME_1)
                .consumeWith(document("updateNickname"));
    }

    @Test
    public void deleteUser() {
        // given
        // when
        when(userService.deleteUser(TEST_ID_1)).thenReturn(Mono.empty());
        // then
        webTestClient.delete().uri("/users/{id}", TEST_ID_1)
                .exchange()
                .expectStatus().isOk().expectBody(Void.class)
                .consumeWith(document("deleteUser"));
    }
}
