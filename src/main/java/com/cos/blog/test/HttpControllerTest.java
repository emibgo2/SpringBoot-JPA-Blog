package com.cos.blog.test;

import org.springframework.web.bind.annotation.*;

// 사용자 요청 -> 응답 (HTML 파일)
// @Controller

// 사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {
    private static final String TAG = "HttpController Test:";
    // 인터넷 브라우저 요청은 무조건 get요청밖에 할 수 없다. 때문에 Postman 사용

    @GetMapping("/http/lombok")
    public String lombokTest() {
        Member m = Member.builder().username("ssar").password("1234").email("emibgo@nate.com").build();
        // Builder 어노테이션은 생성자의 매개변수 순서에 관해서 틀어질일이 없도록 해준다.
        System.out.println(TAG + "getter: " + m.getUsername());
        m.setUsername("cos");
        System.out.println(TAG + "setter: " + m.getUsername());
        return "lombok test 완료";
    }

    @GetMapping("/http/get")
    public String getTest(Member m) {

        return "get 요청: "+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
    }

    @PostMapping("/http/post")
    // Body 형태를 받을 때
    public String postTest(@RequestBody Member m) { // MIME text/plain,
        // application json 을 파싱할때는 MessageConverter (Spring boot)가 해줌
        return "post 요청: "+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
    }

    @PutMapping("/http/put")
    public String putTest() {
        return "put 요청";
    }

    @DeleteMapping("/http/delete" )
    public String deleteTest() {
        return "delete 요청";
    }
}
