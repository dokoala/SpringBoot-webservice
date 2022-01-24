package com.spring.boot.example.web;

import com.spring.boot.example.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // json을 반환하는 컨트롤러로 만들어 준다. @ResponseBody
public class HelloController {

    @GetMapping("/hello") // Http method인 Get의 요청을 받을 수 있는 api를 만들어준다. @RequestMapipng(method = RequestMethod.GET)
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount){
        //@RequestParam은 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션.
        //여기서는 외부에서 name(@RequestParam("name"))이란 이름으로 넘긴 파라메터를
        //메소드 피라미터 name(String name)에 저장하게 된다
        return new HelloResponseDto(name, amount);
    }
}
