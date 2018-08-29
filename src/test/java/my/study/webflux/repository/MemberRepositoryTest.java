package my.study.webflux.repository;

import my.study.webflux.domain.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by leesangpil on 2018. 8. 30..
 */
@RunWith(SpringRunner.class)
@DataMongoTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void findByName() {
        Member member = new Member();
        member.setName("stonegyu");
        memberRepository.save(member).block();

        Member byName = memberRepository.findByName(member.getName()).block();
        assertThat(byName).isNotNull();
        assertThat(byName.getName()).isEqualTo("stonegyu");
    }
}