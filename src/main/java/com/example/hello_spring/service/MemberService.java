package com.example.hello_spring.service;

import com.example.hello_spring.domain.Member;
import com.example.hello_spring.repository.MemberRepository;
import com.example.hello_spring.repository.MemoryMemberRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class MemberService {

  private final MemberRepository memberRepository;

  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  /*
   * 회원가입
   * 중복이름 체크
   */
  public Long join (Member member) {
    duplicateMember(member);
    memberRepository.save(member);
    return member.getId();
  }

  private void duplicateMember(Member member) {
    memberRepository.findByName(member.getName())
      .ifPresent(m -> {
        throw new IllegalStateException("이미 존재하는 회원입니다.");
      });
  }

  /*
   * 전체 회원 조회
   */
  public List<Member> findMembers() {
    return memberRepository.findAll();
  }

  /**
   * 회원 찾기
   */
  public Optional<Member> findOne(Long memberId) {
    return memberRepository.findById(memberId);
  }

}
