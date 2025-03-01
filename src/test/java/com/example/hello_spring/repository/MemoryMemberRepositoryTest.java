package com.example.hello_spring.repository;

import com.example.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class MemoryMemberRepositoryTest {

  MemoryMemberRepository repository = new MemoryMemberRepository();

  @AfterEach
  public void afterEach() {
    // 테스트 하나가 끝날때마다 깨끗하게 초기화
    repository.clearStore();
  }

  @Test
  public void save() {
    Member member = new Member();
    member.setName("spring");
    repository.save(member);
    Member result = repository.findById(member.getId()).get();
    Assertions.assertEquals(member, result);
  }

  @Test
  public void findByName() {
    Member member1 = new Member();
    member1.setName("test");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("test2");
    repository.save(member2);

    Member result = repository.findByName("test").get();
    Assertions.assertEquals(member1, result);
  }

  @Test
  public void findAll() {
    Member member1 = new Member();
    member1.setName("이태호");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("한동훈");
    repository.save(member2);

    List<Member> result = repository.findAll();

    Assertions.assertEquals(result.size(), 2);
  }

}
