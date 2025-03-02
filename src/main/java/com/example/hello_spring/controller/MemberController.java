package com.example.hello_spring.controller;

import com.example.hello_spring.domain.Member;
import com.example.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 1. 스프링 빈(Bean) 이란?
 * = 스프링 컨테이너가 관리하는 자바의 객체를 말한다.
 * = 일반적으로 개발자는 new 연산자를 사용하여 객체를 생성, 메서드를 호출했다.
 * = 스프링의 특징인 IoC(제어의역전) 객체의 생성 및 제어권을 스프링에게 맡기게 된다. 이를 (Bean) 객체라 한다.
 *========================================================================================
 * 2. @Component 어노테이션이 붙어있으면 스프링 빈에 등록되게 된다. ( 컴포넌트 스캔 ) - 스프링 빈 등록방식(1)
 * = @Controller, @Service, @Repository(구현체) 에 붙여준다.
 *========================================================================================
 * 3. 자바코드로 직접 빈에 등록하기 - 스프링 빈 등록방식(2)
 * = SpringConfig 라는 파일 생성 및 @Configuration 어노테이션 추가
 * = Bean에 등록할 요소 추가
 * = 컴포넌트 스캔보다 장점은, 수정이 더 편한거같음. 현재 Repository 구현체로 MemoryRepository를 가지고있는데 DB를 변경한다던가로 파일을 바꾸게되면
 * Component 어노테이션을 바꾸는게 아니라 Config에서 MemoryRepository를 변경될 Repository구현체로 바꿔주면 된다.
 * =======================================================================================
 * 4. DI ( 의존성 주입 ) 의 방식
 * - 생성자 주입 : 가장 권장되는 방식. 어플리케이션이 조립되는 시점에 한번 호출되어 세팅된다.
 * - setter 주입 : public 으로 메서드가 열려있어서 누구나 접근이 가능이 된다. (수정이 가능)
 * - 필드 주입 private memberService memberService; 앞에 @Autowired 어노테이션을 붙인다.
 * => 의존관계가 동적으로 변경되는 경우가 거의 없기에 생성자 주입을 권장한다.
 * ========================================================================================
 * 5. 스프링 컨테이너에 올라간(등록) 되어있어야만 @Autowired 가 실행된다.
 */

@Controller
public class MemberController {

  private final MemberService memberService;

  // 생성자에 Autowired 어노테이션이 붙어있으면, 스프링이 연관된 객체를 스프링 컨테이너에서 찾아 넣어준다.
  // 이와같이 외부에서 객체 의존관계를 넣어주는 것을 DI ( 의존성 주입 ) 이라 한다.
  @Autowired
  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  @GetMapping("/members/new")
  public String createForm() {
    return "members/createMemberForm";
  }

  @PostMapping("/members/new")
  public String create(MemberForm form) {
    Member member = new Member();
    member.setName(form.getName());
    memberService.join(member);
    return "redirect:/";
  }

  @GetMapping("/members")
  public String list(Model model) {
    List<Member> members = memberService.findMembers();
    model.addAttribute("members", members);
    return "members/memberList";
  }
}
