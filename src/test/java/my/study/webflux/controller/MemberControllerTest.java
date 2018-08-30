package my.study.webflux.controller;

import my.study.webflux.domain.Member;
import my.study.webflux.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

/**
 * Created by leesangpil on 2018. 8. 30..
 */
@RunWith(SpringRunner.class)
@WebFluxTest(MemberController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class MemberControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    MemberRepository memberRepository;

    @Test
    public void readAll() {
        Member member = new Member();
        member.setName("splee");
        BDDMockito.given(memberRepository.findAll())
                .willReturn(Flux.just(member));

        webTestClient.get().uri("/members/all").accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(document("readAll"));
    }
}
