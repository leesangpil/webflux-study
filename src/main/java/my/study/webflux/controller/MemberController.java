package my.study.webflux.controller;

import lombok.RequiredArgsConstructor;
import my.study.webflux.domain.Member;
import my.study.webflux.repository.MemberRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * Created by leesangpil on 2018. 8. 30..
 */
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/all")
    public Flux<Member> readAll() {
        return memberRepository.findAll();
    }

}
