package com.example.hello_spring.repository;

import com.example.hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
  Member save(Member member); // 회원을 저장
  Optional<Member> findById(Long id); // id 값으로 회원을 조회
  Optional<Member> findByName(String name); // 이름을 사용하여 회원을 조회
  List<Member> findAll(); // 모든 회원 리스트 조회
}
