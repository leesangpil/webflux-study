package my.study.webflux.controller;

import lombok.RequiredArgsConstructor;
import my.study.webflux.domain.Member;
import my.study.webflux.repository.MemberRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Created by leesangpil on 2018. 8. 30..
 */
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/{name}")
    public Mono<Member> read(@PathVariable String name) {
        return memberRepository.findByName(name).log();
    }

}
