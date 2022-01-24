package com.spring.boot.example.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
// 테스트를 진행할 떄 JUnit 에 내장된 실행자 외에 다른 실행자를 실행시킨다
// 스프링 부트 테스트와 JUnit 사이에 연결자 역할을 함.
@WebMvcTest(controllers = HelloController.class)
//web(spring mvc)에 집중할수 있는 어노테이션
//선언할 경우 @Controller @ControllerAdivce 등을 사용할 수 있습니다.
//@Service @Component @Repository등은 사용할 수 없음.
public class HelloControllerTest {

    @Autowired //  스프링이 관리하는 빈을 주입받음
    private MockMvc mvc; // 웹 API를 테스트할 떄 사용. 스프링 MVC 테스트의 시작점.
    //이걸 통해 HTTP GET, POST 등에 대한 API 테스트를 할 수 있음.

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // /hello로 get 요청
                .andExpect(status().isOk()) // mvc.perform의 결과를 검증(200 404 500 상태검증) 여기선 200 검증
                // HTTP Header의 status를 검증
                .andExpect(content().string(hello)); // mvc.perform의 결과를 검증
                //응답 본문의 내용을 검증. Controller에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증.
    }

    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name) //API 테스트할 때 사용될 요청 파라미터를 설정.
                        // 단, 값은 String만 허용
                        // 그래서 숫자/날짜 등의 데이터도 등록할 때는 문자열로 변경해야만 가능하다.
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
                //jsonpath : Json 응답값을 필드별로 검증할 수 있는 메소드.
                //$를 기준으로 필드명을 명시한다.
                //여기서는 name과 amount를 검증하니 $.name, $.amount로 검증함
    }
}
