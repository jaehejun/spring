package com.example.springdemo;

import com.example.springdemo.config.QuerydslConfig;
import com.example.springdemo.entity.Member;
import com.example.springdemo.repository.MemberRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Import(QuerydslConfig.class) // @DataJpaTest에서는 명시적으로 import 해야함
//@SpringBootTest
@Log4j2
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void insertMember_success() {
        Member member = Member.builder().email("conan@aaa.com")
                .password("1111")
                .name("conan")
                .build();
        memberRepository.save(member);
        Optional<Member> found = memberRepository.findByEmail("conan@aaa.com");
        assertTrue(found.isPresent());
    }

//    @Test
//    public void insertMembers() {
//        IntStream.rangeClosed(1, 100).forEach(i -> {
//            Member member = Member.builder().email("user" + i + "@aaa.com")
//                    .password("1111")
//                    .name("USER" + i)
//                    .build();
//            memberRepository.save(member);
//        });
//    }
}
