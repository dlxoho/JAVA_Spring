package com.example.hello_spring.service;

import com.example.hello_spring.domain.Member;
import com.example.hello_spring.repository.MemberRepository;
import com.example.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

  MemberService memberService;
  MemoryMemberRepository memberRepository;

  @BeforeEach
  public void beforeEach() {
    memberRepository = new MemoryMemberRepository();
    memberService = new MemberService(memberRepository);
  }

  @AfterEach
  public void afterEach() {
    memberRepository.clearStore();
  }

  @Test
  void join() {
    // given
    Member member = new Member();
    member.setName("test");

    // when
    Long saveId = memberService.join(member);

    // then
    Member findMember = memberService.findOne(saveId).get();
    Assertions.assertEquals(member.getName(), findMember.getName());
  }

  @Test
  public void 중복회원예외() {
    // given
    Member member = new Member();
    member.setName("이태호");

    Member member2 = new Member();
    member2.setName("이태호");

    // when
    memberService.join(member);
    try {
      memberService.join(member2);
      fail();
    } catch (Exception e) {
      Assertions.assertEquals(e.getMessage(), "이미 존재하는 회원입니다.");
    }
  }

  @Test
  void findMembers() {
  }

  @Test
  void findOne() {
  }
}