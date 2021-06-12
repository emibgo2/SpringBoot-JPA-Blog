package com.cos.blog.controller.api;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @PostMapping("/api/user")
    public ResponseDto save(@RequestBody User user) {
        System.out.println("UserApiController: save 호출됨");
        // 실제로 DB에  insert를 하고 아래에서 return 이 되면된다.
        return new ResponseDto<Integer>(HttpStatus.OK,1);
    }
}
