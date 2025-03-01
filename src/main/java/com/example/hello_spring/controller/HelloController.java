package com.example.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

  @GetMapping("hello") // get 요청
  public String hello(Model model) {
    // Model 객체란 Controller에서 생성된 데이터를 담아서 View로 전달할때 사용하는 객체
    model.addAttribute("data", "hello!");
    return "hello";
  }

  // MVC 방식
  // MVC : Model View Controller (관심사를 분리)
  @GetMapping("hello-mvc")
  public String helloMvc(@RequestParam("name") String name, Model model) {
    model.addAttribute("name", name);
    return "hello-template";
  }

  // API 방식
  @GetMapping("hello-string")
  @ResponseBody // http 통신의 response body 부분에 내용을 직접 넣어줌을 의미한다.
  public String helloString(@RequestParam("name") String name) {
    return "hello" + name;
  }

  @GetMapping("hello-api")
  @ResponseBody // 해당 어노테이션이 존재하는 경우 ViewResolver 가 아닌 HttpMessageConverter 가 동작한다. ( 반환데이터가 문자면 StringConverter, 객체면 JsonConverter 동작 )
  public Hello HelloApi(@RequestParam("name") String name) {
    Hello hello = new Hello();
    hello.setName(name);
    return hello; // json 데이터 타입으로 반환되게 된다.
  }

  static class Hello {
    // private 변수이기 때문에 외부에서 바로 접근이 불가
    // getter , setter 메서드를 사용한 방식으로 접근 (java bean 표준방식)
    private String name;

    public String getName() {
      return name;
    }
    public void setName(String name) {
      this.name = name;
    }
  }

}
