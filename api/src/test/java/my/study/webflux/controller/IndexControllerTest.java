package my.study.webflux.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

/**
 * Created by leesangpil on 2018. 8. 30..
 */
@RunWith(SpringRunner.class)
@WebFluxTest(IndexController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class IndexControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    public void index() {
        webTestClient.get().uri("/").exchange()
                .expectStatus().isOk().expectBody()
                .consumeWith(document("index"));
    }
}
